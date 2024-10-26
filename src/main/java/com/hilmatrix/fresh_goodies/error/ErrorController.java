package com.hilmatrix.fresh_goodies.error;

import com.hilmatrix.fresh_goodies.response.ApiConstants;
import com.hilmatrix.fresh_goodies.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice
public class ErrorController {

    // Handle 404 errors (endpoint not found)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(NoHandlerFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(
                ApiConstants.STRING_NOT_FOUND,
                ApiConstants.STATUS_NOT_FOUND,
                false,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle all other exceptions (Java crashes, etc.)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        String stackTrace = Arrays.toString(ex.getStackTrace());

        ApiResponse<String> response = new ApiResponse<>(
                ApiConstants.STRING_ERROR,
                ApiConstants.STATUS_ERROR,
                false,
                stackTrace
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}