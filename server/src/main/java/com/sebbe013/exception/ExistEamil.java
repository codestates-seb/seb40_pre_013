package com.sebbe013.exception;

public class ExistEamil extends RuntimeException {
    private static final String MESSAGE = "이미 존재하는 이메일 입니다.";

    public ExistEamil(){ //메세지만
        super(MESSAGE);
    }

    public ExistEamil( Throwable cause ){ //메시지와 원인??
        super(MESSAGE, cause);
    }
}
