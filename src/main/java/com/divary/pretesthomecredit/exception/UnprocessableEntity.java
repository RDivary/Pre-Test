package com.divary.pretesthomecredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntity extends ErrorException {

    public UnprocessableEntity(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
