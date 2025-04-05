package com.soumyajit.ISA.HIT.HALDIA.Advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T>{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    private T data;
    private ApiError apiError;


    public ApiResponse() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }
}
