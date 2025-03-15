package com.example.spring_test.common.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {

    public int code;

    public String message;

    public T data;

    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(0, "SUCCESS", data);
    }

    public static <T> Response<T> failed(int code, String message) {
        return new Response<>(code, message, null);
    }

    public static <T> Response<T> failed(String message) {
        return new Response<>(500, message, null);
    }

    public static <T> Response<T> failed(Throwable t) {
        String message = t.getMessage();
        if (StringUtils.isBlank(message)) {
            message = t.getClass().getSimpleName();
        }
        return new Response<>(500, message, null);
    }
}


