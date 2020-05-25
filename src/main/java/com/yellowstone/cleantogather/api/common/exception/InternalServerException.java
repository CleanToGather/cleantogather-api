package com.yellowstone.cleantogather.api.common.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseHttpException {
    public InternalServerException(String message) { super(HttpStatus.INTERNAL_SERVER_ERROR, message); }

    public InternalServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }
}
