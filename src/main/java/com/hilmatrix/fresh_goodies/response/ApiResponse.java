package com.hilmatrix.fresh_goodies.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String message;
    private int status;
    private boolean success;
    private T data;

    public ApiResponse(String message, int status, boolean success, T data) {
        this.message = message;
        this.status = status;
        this.success = success;
        this.data = data;
    }

    public ApiResponse() {

    }

    public static <T> ApiResponse<T> Success(String message, T data) {
        return new ApiResponse<>(message, ApiConstants.STATUS_SUCCESS, true, data);
    }

    public static <T> ApiResponse<T> NotFound(String message, T data) {
        return new ApiResponse<>(message, ApiConstants.STATUS_NOT_FOUND, false, data);
    }

    public static <T> ApiResponse<T> Conflict(String message, T data) {
        return new ApiResponse<>(message, ApiConstants.STATUS_CONFLICT, false, data);
    }
}
