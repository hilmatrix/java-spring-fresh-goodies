package com.hilmatrix.fresh_goodies.products;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductConstants {
    public static final String MESSAGE_SUCCESS_GET_ALL = "Success returning all products";
    public static final String MESSAGE_SUCCESS_GET = "Success returning one product";
    public static final String MESSAGE_SUCCESS_CREATE = "Success created product";
    public static final String MESSAGE_SUCCESS_UPDATE = "Success updated product";
    public static final String MESSAGE_SUCCESS_DELETED = "Success deleted product";
    public static final String MESSAGE_FAILED_NOT_FOUND = "Product with this ID is not found";
    public static final String MESSAGE_FAILED_ALREADY_EXIST = "Product with this ID is already exist";

    public static final List<String> VALID_SORT_FIELDS = Collections.unmodifiableList(
            Arrays.asList("name", "weight", "price")
    );
}
