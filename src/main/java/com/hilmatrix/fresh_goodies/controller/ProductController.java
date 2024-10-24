package com.hilmatrix.fresh_goodies.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    // GET method to retrieve products
    @GetMapping
    public String getAllProducts() throws IOException {
        return "Products";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id) {
        return "Product id " + id;
    }

    // POST method to create a new product
    @PostMapping
    public String createProduct() {
        return "Product created";
    }

    // PUT method to update an existing product
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        // Add your logic to update the product with the given id
        return "Product with id " + id + " updated";
    }

    // DELETE method to delete a product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        // Add your logic to delete the product with the given id
        return "Product with id " + id + " deleted";
    }
}
