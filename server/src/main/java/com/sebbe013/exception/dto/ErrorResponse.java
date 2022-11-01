package com.sebbe013.exception.dto;

import lombok.Builder;
import lombok.Getter;

/*
클라이언트에게 보내줄 에러 정보 클래스
 */
@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)//비어있으면 정보를 내리지 않음.
public class ErrorResponse {

    private int code;
    private String message;

    @Builder
    public ErrorResponse( int code, String message ){
        this.code = code;
        this.message = message;
    }
}
