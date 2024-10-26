package com.hilmatrix.fresh_goodies.products;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metadata_id")
    private MetadataEntity metadata;

    public void copyFrom(ProductEntity other) {
        this.price = other.price;
        this.weight = other.weight;
        this.name = other.name;
        this.category = other.category;
        this.imageUrl = other.imageUrl;

        if (other.getMetadata() != null) {
            if (this.metadata == null) {
                this.metadata = new MetadataEntity(); // Create a new instance if it doesn't exist
            }
            this.metadata.copyFrom(other.getMetadata()); // Update metadata
        }
    }
}