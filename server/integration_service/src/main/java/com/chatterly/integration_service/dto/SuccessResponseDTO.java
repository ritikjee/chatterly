package com.chatterly.integration_service.dto;

import org.springframework.http.HttpStatus;

public class SuccessResponseDTO<T> {

    private HttpStatus status;
    private T data;

    public SuccessResponseDTO() {
    }

    public SuccessResponseDTO(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}