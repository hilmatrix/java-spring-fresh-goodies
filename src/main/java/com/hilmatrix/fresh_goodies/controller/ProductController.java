package com.hilmatrix.fresh_goodies.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilmatrix.fresh_goodies.JsonData;
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

    @GetMapping
    public List<Map<String, Object>> getAllProducts() throws IOException {
        return JsonData.instance.products != null ? JsonData.instance.products : new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable int id) {
        Optional<Map<String, Object>> product = JsonData.instance.products.stream()
                .filter(p -> Integer.parseInt((String) p.get("id")) == id)
                .findFirst();

        return product.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PostMapping
    public String createProduct() {
        return "Product created";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        // Add your logic to update the product with the given id
        return "Product with id " + id + " updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        // Add your logic to delete the product with the given id
        return "Product with id " + id + " deleted";
    }
}
