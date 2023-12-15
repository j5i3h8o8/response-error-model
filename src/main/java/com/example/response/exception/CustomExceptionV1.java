package com.example.response.exception;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.AbstractMap;
import java.util.Map;

public class CustomExceptionV1 extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;
    private String message;

    // map 형태로 제공될 이유는 사실 없습니다. 서비스마다 정책을 잡으면 되는 부분입니다.
    @Getter
    private Map.Entry<String, Object> data;

    public CustomExceptionV1(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomExceptionV1(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public CustomExceptionV1(ErrorCode errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = new AbstractMap.SimpleEntry<>(data.getClass().getSimpleName(), data);
    }

    @Override
    public String getMessage() {
        if (StringUtils.hasLength(this.message)) {
            return this.message;
        }
        return errorCode.getMessage();
    }
}
