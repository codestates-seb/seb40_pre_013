package com.sebbe013.exception.bussiness;

import lombok.Getter;
import org.springframework.http.HttpStatus;
/*
예외 상태 코드와 메시지 관리 enum
 */

public enum ExceptionCode {
    EXIST_EMAIL(HttpStatus.CONFLICT.value(), "이미 존재하는 e-mail입니다."),
    EXIST_DISPLAY_NAME(HttpStatus.CONFLICT.value(), "이미 존재하는 Display name입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "찾을 수 없는 e-mail입니다.");
    @Getter
    private int code;
    @Getter
    private String message;

    ExceptionCode( int code, String message ){
        this.code = code;
        this.message = message;
    }
}
