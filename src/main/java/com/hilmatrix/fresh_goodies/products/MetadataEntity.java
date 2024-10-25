package com.hilmatrix.fresh_goodies.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MetadataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private String unit;
    private int weight;
    private int calorie;
    private double proteins;
    private double fats;
    private int increment;
    private double carbs;

    // Getters and Setters
}