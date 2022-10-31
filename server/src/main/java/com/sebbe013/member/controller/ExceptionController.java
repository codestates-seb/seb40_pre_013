package com.sebbe013.member.controller;

import com.sebbe013.stackoverflowclone.member.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;
//글로벌 예외 처리 클래스
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException( MethodArgumentNotValidException e){

        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ErrorResponse.FieldError> errors = fieldErrors.stream()
                .map(error -> new ErrorResponse.FieldError(
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()
                )).collect(Collectors.toList());

        return  new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);

    }



}
