package com.sebbe013.exception;

public class ExistDisplayNameExeption extends RuntimeException {
    private final static String MESSAGE = "이미 존재하는  Display Name입니다.";

    public ExistDisplayNameExeption(){
        super(MESSAGE);
    }

    public ExistDisplayNameExeption( Throwable cause ){
        super(MESSAGE, cause);
    }
}
