package com.yellowstone.cleantogather.api.common.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseHttpException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
