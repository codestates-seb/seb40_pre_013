package com.sebbe013.exception.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)//비어있으면 정보를 내리지 않음.
public class ErrorResponse {

    private int code;
    private String message;
    Map<String, String> errors = new HashMap<>();

    @Builder
    public ErrorResponse( int code, String message, Map<String, String> errors ){
        this.code = code;
        this.message = message;
        this.errors = errors;
    }
}
