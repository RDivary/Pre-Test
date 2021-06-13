package com.divary.pretesthomecredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ErrorException {

    public NotFoundException(String model) {
        super(model + " not found", HttpStatus.NOT_FOUND);
    }
}
