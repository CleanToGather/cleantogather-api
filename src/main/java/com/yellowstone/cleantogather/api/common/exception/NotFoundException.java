package com.yellowstone.cleantogather.api.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseHttpException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "This resource could not be found");
    }
}
