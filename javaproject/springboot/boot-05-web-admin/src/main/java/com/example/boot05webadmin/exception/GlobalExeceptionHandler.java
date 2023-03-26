package com.example.boot05webadmin.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理整个web controller的异常
 */
@ControllerAdvice
public class GlobalExeceptionHandler {
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})//处理异常
    public String handleArithException() {
        return "login";//视图地址
    }
}
