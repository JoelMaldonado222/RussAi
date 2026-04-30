package com.russai.russai.model;

import jakarta.persistence.*; //database mapping
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

// Lombok auto-generates getters, setters, toString, and constructors
@Data
@Entity
@Table(name = "spirits")          // The JPA entity that maps your Java code to the spirits table in PostgreSQL.
public class Spirit {             // This is how Spring Boot reads and writes to your database.

    // Marks this field as the Primary Key and auto-generates a secure UUID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "spirit_id")
    private UUID spiritId;

    // Standard fields automatically map to columns with the same name
    private String name;
    private String category;
    private String distillery;

    // "TEXT" definition allows for longer content than standard VARCHAR(255)
    @Column(name = "mash_bill", columnDefinition = "TEXT")
    private String mashBill;

    @Column(name = "flavor_tags", columnDefinition = "TEXT")
    private String flavorTags;

    // BigDecimal is used for financial/precise values to prevent rounding errors
    @Column(name = "price_pour")
    private BigDecimal pricePour;

    private BigDecimal proof;

    // Using Integer instead of primitive 'int' allows for null (No Age Statement)
    @Column(name = "age_statement")
    private Integer ageStatement;

    @Column(name = "batch_type")
    private String batchType;

    private String finish;
}