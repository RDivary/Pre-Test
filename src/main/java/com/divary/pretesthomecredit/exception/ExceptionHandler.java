package com.divary.pretesthomecredit.exception;

import com.divary.pretesthomecredit.controller.BaseController;
import com.divary.pretesthomecredit.dto.response.BaseResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Objects;

@ControllerAdvice
public class ExceptionHandler extends BaseController {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ErrorException.class})
    public ResponseEntity<BaseResponse<?>> exceptionHandler(ErrorException e) {
        return super.getResponse(null, e.getMessage(), e.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {PropertyReferenceException.class})
    public ResponseEntity<BaseResponse<?>> PropertyReferenceExceptionHandler(PropertyReferenceException e) {
        return super.getResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<BaseResponse<?>> PropertyReferenceExceptionHandler(HttpMessageNotReadableException e) {

        try {
            String errorMessage = Objects.requireNonNull(e.getMessage()).split(";")[0];

            return super.getResponse(null, errorMessage, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return super.getResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
