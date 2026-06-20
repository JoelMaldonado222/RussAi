package com.russai.russai.repository;


//One interface,  Gives database queries for free via Spring Data JPA

import com.russai.russai.model.Spirit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

}