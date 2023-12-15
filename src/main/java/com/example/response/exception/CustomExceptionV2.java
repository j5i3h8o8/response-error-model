package com.example.response.exception;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 실제 현업에서 에러 데이터관리 하기위해서 ThreadLocal 를 사용하는 경우는 드뭅니다.
 * 하지만, ThreadLocal 의 학습과 사용 경험을 위해서 나온 하위 과제입니다.
 */
public class CustomExceptionV2 extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;
    private String message;

    public CustomExceptionV2(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomExceptionV2(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public CustomExceptionV2(ErrorCode errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        ExceptionContext.threadLocal.get().put(data.getClass().getSimpleName(), data);
    }

    @Override
    public String getMessage() {
        if (StringUtils.hasLength(this.message)) {
            return this.message;
        }
        return errorCode.getMessage();
    }
}
