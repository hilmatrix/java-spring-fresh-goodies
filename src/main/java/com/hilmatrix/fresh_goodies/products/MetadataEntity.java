package com.hilmatrix.fresh_goodies.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "metadata")
@Data
public class MetadataEntity {
    @Id
    private String id;
    private String unit;
    private int weight;
    private int calorie;
    private double proteins;
    private double fats;
    private int increment;
    private double carbs;

    public void copyFrom(MetadataEntity other) {
        this.unit = other.unit;
        this.weight = other.weight;
        this.calorie = other.calorie;
        this.proteins = other.proteins;
        this.fats = other.fats;
        this.increment = other.increment;
        this.carbs = other.carbs;
    }
}