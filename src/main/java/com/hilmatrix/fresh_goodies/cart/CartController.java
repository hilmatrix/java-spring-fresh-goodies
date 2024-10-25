package com.hilmatrix.fresh_goodies.cart;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {


    @GetMapping
    public String getCartItems() throws IOException {
        return "Cart items is working";
    }

    @GetMapping("/{id}")
    public String getCartItem(@PathVariable int id) {
        return "get Cart item ID = " + id;
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
