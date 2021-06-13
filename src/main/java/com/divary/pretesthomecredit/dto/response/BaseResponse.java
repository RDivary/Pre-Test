package com.divary.pretesthomecredit.dto.response;

import java.time.LocalDateTime;

public class BaseResponse<T> {

    private T data;

    private int code;

    private String status;

    private String message;

    private final LocalDateTime time = LocalDateTime.now();

    public BaseResponse(T data, int code, String status, String message) {
        this.data = data;
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
