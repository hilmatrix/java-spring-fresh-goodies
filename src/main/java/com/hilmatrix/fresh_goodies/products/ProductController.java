package com.hilmatrix.fresh_goodies.products;

import com.hilmatrix.fresh_goodies.response.ApiResponse;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.hilmatrix.fresh_goodies.products.ProductConstants.VALID_SORT_FIELDS;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ApiResponse<List<ProductEntity>> getAllProducts(
            @RequestParam(required = false, defaultValue = "none") String _sort,
            @RequestParam(required = false, defaultValue = "asc") String _order) throws IOException {
        List<ProductEntity> products;
        boolean applySort = VALID_SORT_FIELDS.contains(_sort);

        if (applySort) {
            Sort.Direction direction = "desc".equalsIgnoreCase(_order) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = Sort.by(direction, _sort);
            products = productRepository.findAll(sort);
        } else {
            products = productRepository.findAll();
        }

        return ApiResponse.Success(
                ProductConstants.MESSAGE_SUCCESS_GET_ALL,
                products
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductEntity> getProduct(@PathVariable int id) {
        if (productRepository.existsById(String.valueOf(id))) {
            return  ApiResponse.Success(
                    ProductConstants.MESSAGE_SUCCESS_GET,
                    productRepository.getById(String.valueOf(id))
            );
        } else {
            return  ApiResponse.NotFound(
                    ProductConstants.MESSAGE_FAILED_NOT_FOUND + " = " + id,
                    null);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        if (productRepository.existsById(String.valueOf(product.getId())))
            return ApiResponse.Conflict(ProductConstants.MESSAGE_FAILED_ALREADY_EXIST + " = " + product.getId(), product);

        product.getMetadata().setId(product.getId());
        ProductEntity createdProduct = productRepository.save(product);

        return  ApiResponse.Success(
                ProductConstants.MESSAGE_SUCCESS_CREATE,
                createdProduct
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<ProductEntity> updateProduct(@RequestBody ProductEntity updatedProduct) {
        String id = updatedProduct.getId();

        if (!productRepository.existsById(id))
            return ApiResponse.NotFound(ProductConstants.MESSAGE_FAILED_NOT_FOUND + " = " + id, null);

        ProductEntity productFromDB = productRepository.getById(id);
        productFromDB.copyFrom(updatedProduct);
        ProductEntity savedProduct = productRepository.save(productFromDB);

        return ApiResponse.Success(
                ProductConstants.MESSAGE_SUCCESS_UPDATE,
                savedProduct
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable String id) {
        if (!productRepository.existsById(id))
            return ApiResponse.NotFound(ProductConstants.MESSAGE_FAILED_NOT_FOUND + " = " + id, null);

        ProductEntity product = productRepository.getById(id);
        productRepository.delete(product);

        return ApiResponse.Success(ProductConstants.MESSAGE_SUCCESS_DELETED + " = " + id, null);
    }
}
