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
@RequestMapping("/api/v1/cart")
public class CartController {


    @GetMapping
    public List<Map<String, Object>> getCartItems() throws IOException {
        return JsonData.instance.cart != null ? JsonData.instance.cart : new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCartItem(@PathVariable int id) {
        Optional<Map<String, Object>> product = JsonData.instance.cart.stream()
                .filter(p -> Integer.parseInt((String) p.get("id")) == id)
                .findFirst();

        return product.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PostMapping
    public String createCartItem() {
        return "Product created";
    }

    @PutMapping("/{id}")
    public String updateCart(@PathVariable Long id) {
        return "Cart with id " + id + " updated";
    }

    @DeleteMapping("/{id}")
    public String deleteCartItem(@PathVariable Long id) {
        return "Cart with id " + id + " deleted";
    }
}
