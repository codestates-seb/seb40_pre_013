package com.sebbe013.exception.bussiness;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {

    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
