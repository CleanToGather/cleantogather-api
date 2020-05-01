package com.yellowstone.cleantogather.api.common.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseHttpException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BaseHttpException() {}

    public BaseHttpException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
