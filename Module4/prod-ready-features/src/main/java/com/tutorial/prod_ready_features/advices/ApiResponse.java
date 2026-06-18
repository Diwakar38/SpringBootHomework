package com.tutorial.prod_ready_features.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private T data;
    private ApiError error;
    private LocalDateTime timeStamp;

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }
}
