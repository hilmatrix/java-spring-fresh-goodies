package com.hilmatrix.fresh_goodies.cart;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartEntity {
    @Id
    private String id;
    private String productId;
    private int quantity;
    // Getters and Setters
}

