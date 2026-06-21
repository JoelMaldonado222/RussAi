package com.russai.russai.repository;


//One interface,  Gives database queries for free via Spring Data JPA

import com.russai.russai.model.Spirit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

// I use this to tell Spring: "This is my middleman for the database."
@Repository
public interface SpiritRepository extends JpaRepository<Spirit, UUID> {

    /* By extending JpaRepository, I get these methods for free:
       - .save()      <- I use this to add a new spirit
       - .findAll()   <- I use this to see my whole collection
       - .findById()  <- I use this to grab one specific spirit

       I put <Spirit, UUID> here so Spring knows I'm
       managing the "Spirit" model via its "UUID" ID.
    */

    // Stores a precomputed embedding into the pgvector column for one spirit.
    //
    // This is a native query because the embedding column is a pgvector
    // 'vector' type, which Hibernate does not map. The vector arrives as a
    // string formatted like "[0.1,0.2,0.3]" and is cast to the vector type
    // inside Postgres. @Modifying marks this as a write, and @Transactional
    // lets the update commit on its own.
    @Modifying
    @Transactional
    @Query(value = "UPDATE spirits SET embedding = CAST(:embedding AS vector) WHERE spirit_id = :spiritId",
           nativeQuery = true)
    void updateEmbedding(@Param("spiritId") UUID spiritId,
                         @Param("embedding") String embedding);

    // Reads back the stored embedding for one spirit as a plain string, e.g.
    // "[0.1,0.2,0.3]". The vector type itself can't be mapped to a Java field,
    // so it's cast to text inside Postgres before coming back over JDBC. This
    // string is what gets reused as the query vector in findSimilarByEmbedding.
    @Query(value = "SELECT CAST(embedding AS text) FROM spirits WHERE spirit_id = :spiritId",
           nativeQuery = true)
    String getEmbeddingAsString(@Param("spiritId") UUID spiritId);

    // Finds the spirits whose flavor embedding is closest to a given query
    // vector, using pgvector's cosine distance operator (<=>). Smaller
    // distance = more similar flavor. Excludes the spirit being compared
    // against itself. Selected columns match the Spirit entity's mapped
    // fields exactly (embedding is left out since it isn't a Java field).
    @Query(value = "SELECT spirit_id, name, category, distillery, mash_bill, flavor_tags, " +
                   "price_pour, proof, age_statement, batch_type, finish " +
                   "FROM spirits " +
                   "WHERE spirit_id != :excludeId AND embedding IS NOT NULL " +
                   "ORDER BY embedding <=> CAST(:queryVector AS vector) " +
                   "LIMIT :limit",
           nativeQuery = true)
    List<Spirit> findSimilarByEmbedding(@Param("excludeId") UUID excludeId,
                                        @Param("queryVector") String queryVector,
                                        @Param("limit") int limit);

}