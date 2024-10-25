package com.hilmatrix.fresh_goodies.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {
    @Id
    private String id;
    private double price;
    private int weight;
    private String name;
    private String category;
    private String imageUrl;
}