package com.divary.pretesthomecredit.exception;

import org.springframework.http.HttpStatus;

public abstract class ErrorException extends RuntimeException {

    private HttpStatus httpStatus;

    public ErrorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
