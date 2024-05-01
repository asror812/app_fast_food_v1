package com.example.app_fast_food.common.exceptions;

import com.example.app_fast_food.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class FastFoodExceptionHandler {
    @ExceptionHandler(OtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<String> handleEarlyResentException(OtpException e) {
        return CommonResponse.fail( e.getMessage() );
    }


}
