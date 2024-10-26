package com.hilmatrix.fresh_goodies.cart;

import com.hilmatrix.fresh_goodies.products.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cart")
public class CartEntity {
    @Id
    private String id;
    private String productId;
    private int quantity;
    // Getters and Setters
    public void copyFrom(CartEntity other) {
        this.productId = other.productId;
        this.quantity = other.quantity;
    }
}

