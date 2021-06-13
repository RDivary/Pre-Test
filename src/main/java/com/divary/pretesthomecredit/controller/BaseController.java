package com.divary.pretesthomecredit.controller;

import com.divary.pretesthomecredit.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected ResponseEntity<BaseResponse<?>> getResponseCreated(Object data, String message) {

        return getResponse(data, message, HttpStatus.CREATED);

    }

    protected ResponseEntity<BaseResponse<?>> getResponseSuccess(Object data, String message) {

        return getResponse(data, message, HttpStatus.OK);
    }

    protected ResponseEntity<BaseResponse<?>> getResponse(Object data, String message, HttpStatus httpStatus) {

        BaseResponse<?> response = new BaseResponse<>(
                data,
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                message
        );

        return ResponseEntity.status(httpStatus).body(response);
    }
}
