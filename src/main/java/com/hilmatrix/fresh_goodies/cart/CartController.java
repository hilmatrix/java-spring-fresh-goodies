package com.hilmatrix.fresh_goodies.cart;

import com.hilmatrix.fresh_goodies.response.ApiConstants;
import com.hilmatrix.fresh_goodies.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public ApiResponse<List<CartEntity>> getCartItems() throws IOException {
        return ApiResponse.Success(
            CartConstants.MESSAGE_SUCCESS_GET_ALL,
            cartRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<CartEntity> getCartItem(@PathVariable int id) {
        if (!cartRepository.existsById(String.valueOf(id)))
            return new ApiResponse<>().NotFound(CartConstants.MESSAGE_FAILED_NOT_FOUND, null);

        return  ApiResponse.Success(
                CartConstants.MESSAGE_SUCCESS_GET,
                cartRepository.getById(String.valueOf(id))
                );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CartEntity> createCartItem(@RequestBody CartEntity cartItem) {
        if (cartRepository.existsById(String.valueOf(cartItem.getId())))
            return ApiResponse.Conflict(CartConstants.MESSAGE_FAILED_ALREADY_EXIST + " = " + cartItem.getId(), cartItem);

        CartEntity createdCartItem = cartRepository.save(cartItem);

        return  ApiResponse.Success(
                CartConstants.MESSAGE_SUCCESS_CREATE,
                createdCartItem
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<CartEntity> updateCartItem(@RequestBody CartEntity updatedCartItem) {
        String id = updatedCartItem.getId();

        if (!cartRepository.existsById(id))
            return ApiResponse.NotFound(CartConstants.MESSAGE_FAILED_NOT_FOUND + " = " + id, null);

        CartEntity cartFromDB = cartRepository.getById(id);
        cartFromDB.copyFrom(updatedCartItem);
        CartEntity savedCartItem = cartRepository.save(cartFromDB);

        return ApiResponse.Success(
                CartConstants.MESSAGE_SUCCESS_UPDATE,
                savedCartItem
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCartItem(@PathVariable String id) {
        if (!cartRepository.existsById(id))
            return ApiResponse.NotFound(CartConstants.MESSAGE_FAILED_NOT_FOUND + " = " + id, null);

        CartEntity cartItem = cartRepository.getById(id);
        cartRepository.delete(cartItem);

        return ApiResponse.Success(CartConstants.MESSAGE_SUCCESS_DELETED + " = " + id, null);
    }
}
