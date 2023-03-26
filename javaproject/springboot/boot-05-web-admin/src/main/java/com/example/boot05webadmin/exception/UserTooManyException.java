package com.example.boot05webadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "访问用户过多")
 * value自定义返回错误代码
 * reason说明
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "访问用户过多")
public class UserTooManyException extends RuntimeException {
    public UserTooManyException() {
    }

    public UserTooManyException(String message) {
        super(message);
    }
}
