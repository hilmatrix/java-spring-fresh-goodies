package com.hilmatrix.fresh_goodies.favorites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FavoritesEntity {
    @Id
    private String id;
    private int productId;

    // Getters and Setters
}