package com.hilmatrix.fresh_goodies.favorites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "favorite_products")
public class FavoriteEntity {
    @Id
    private String id;
    private int productId;

    public void copyFrom(FavoriteEntity other) {
        this.productId = other.productId;
    }
    // Getters and Setters
}