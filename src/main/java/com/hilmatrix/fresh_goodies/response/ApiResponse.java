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

    public ApiResponse Success(String message, T data) {
        this.message = message;
        status = ApiConstants.STATUS_SUCCESS;
        success = true;
        this.data = data;
        return this;
    }

    public ApiResponse NotFound(String message, T data) {
        this.message = message;
        status = ApiConstants.STATUS_NOT_FOUND;
        success = false;
        this.data = data;
        return this;
    }

    public ApiResponse Conflict(String message, T data) {
        this.message = message;
        status = ApiConstants.STATUS_CONFLICT;
        success = false;
        this.data = data;
        return this;
    }
}
