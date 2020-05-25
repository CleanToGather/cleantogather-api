package com.yellowstone.cleantogather.api.common.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends BaseHttpException {
    public UnprocessableEntityException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

    public UnprocessableEntityException() { super(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid username/password supplied"); }
}
