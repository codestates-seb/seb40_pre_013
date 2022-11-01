package com.sebbe013.exception.controller;

import com.sebbe013.exception.bussiness.BussinessException;
import com.sebbe013.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
예외처리 컨트롤러
 */
@RestControllerAdvice
public class ExceptionController {

    //비지니스 예외 처리 컨트롤러
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<ErrorResponse> bussinessExceptionHandler( BussinessException e ){

        ErrorResponse responseBody = ErrorResponse.builder()
                .code(e.getCode()) //예외 상태 코드 주입
                .message(e.getMessage()) // 메시지 상태코드 주입
                .build();

        ResponseEntity<ErrorResponse> response
                = ResponseEntity.status(e.getCode()).body(responseBody);//리스폰스 엔티티 객체에 상태와 바디를 담는다.

        return response;
    }
}
