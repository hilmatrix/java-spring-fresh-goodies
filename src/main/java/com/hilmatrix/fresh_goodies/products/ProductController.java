package com.hilmatrix.fresh_goodies.products;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductEntity> getAllProducts() throws IOException {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id) {
        return "get Product item ID = " + id;
    }

    @PostMapping
    public String createProduct() {
        return "Product created";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "Product with id " + id + " updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Product with id " + id + " deleted";
    }
}
