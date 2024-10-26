package com.hilmatrix.fresh_goodies.products;

import com.hilmatrix.fresh_goodies.response.ApiResponse;
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

    @GetMapping
    public ApiResponse<List<ProductEntity>> getAllProducts() throws IOException {
        return new ApiResponse().Success(
                ProductConstants.MESSAGE_SUCCESS_RETURN_ALL,
                productRepository.findAll()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductEntity> getProduct(@PathVariable int id) {
        if (productRepository.existsById(String.valueOf(id))) {
            return new ApiResponse().Success(
                    ProductConstants.MESSAGE_SUCCESS_RETURN_ONE,
                    productRepository.getById(String.valueOf(id))
            );
        } else {
            return new ApiResponse().NotFound(ProductConstants.MESSAGE_PRODUCT_NOT_FOUND + " = " + id, null);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        if (productRepository.existsById(String.valueOf(product.getId())))
            return new ApiResponse().Conflict(ProductConstants.MESSAGE_PRODUCT_ALREADY_EXIST + " = " + product.getId(), product);

        product.getMetadata().setId(product.getId());
        ProductEntity createdProduct = productRepository.save(product);

        return new ApiResponse().Success(
                ProductConstants.MESSAGE_SUCCESS_CREATED_PRODUCT,
                createdProduct
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ProductEntity> updateProduct(@RequestBody ProductEntity updatedProduct) {
        String id = updatedProduct.getId();

        if (!productRepository.existsById(id))
            return new ApiResponse().NotFound(ProductConstants.MESSAGE_PRODUCT_NOT_FOUND + " = " + id, null);

        ProductEntity productFromDB = productRepository.getById(id);
        productFromDB.copyFrom(updatedProduct);
        ProductEntity savedProduct = productRepository.save(productFromDB);

        return new ApiResponse().Success(
                ProductConstants.MESSAGE_SUCCESS_UPDATED_PRODUCT,
                savedProduct
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable String id) {
        if (!productRepository.existsById(id))
            return new ApiResponse().NotFound(ProductConstants.MESSAGE_PRODUCT_NOT_FOUND + " = " + id, null);

        ProductEntity product = productRepository.getById(id);
        productRepository.delete(product);

        return new ApiResponse().Success(ProductConstants.MESSAGE_SUCCESS_DELETED_PRODUCT + " = " + id, null);
    }
}
