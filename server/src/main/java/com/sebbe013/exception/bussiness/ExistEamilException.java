package com.sebbe013.exception.bussiness;

public class ExistEamilException extends BussinessException {
    private static final String MESSAGE = ExceptionCode.EXIST_EMAIL.getMessage();

    public ExistEamilException(){ //메세지만
        super(MESSAGE);
    }

    @Override
    public int getCode(){
        return ExceptionCode.EXIST_EMAIL.getCode();
    }
}
