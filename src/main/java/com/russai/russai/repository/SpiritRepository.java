package com.russai.russai.repository;


//One interface,  Gives database queries for free via Spring Data JPA

import com.russai.russai.model.Spirit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
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
    
}