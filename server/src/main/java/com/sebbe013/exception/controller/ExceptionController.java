package com.sebbe013.exception.controller;

import com.sebbe013.exception.bussiness.BussinessException;
import com.sebbe013.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ExceptionController {
    //글로벌 예외

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<ErrorResponse> bussinessExceptionHandler( BussinessException e ){

        ErrorResponse responseBody = ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();

        ResponseEntity<ErrorResponse> response = ResponseEntity.status(e.getCode()).body(responseBody);

        return response;
    }
}
