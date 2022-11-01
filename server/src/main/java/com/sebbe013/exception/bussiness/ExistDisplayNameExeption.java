package com.sebbe013.exception.bussiness;

public class ExistDisplayNameExeption extends BussinessException{
    private final static String MESSAGE = ExceptionCode.EXIST_DISPLAY_NAME.getMessage();

    @Override
    public int getCode(){
        return ExceptionCode.EXIST_EMAIL.getCode();
    }

    public ExistDisplayNameExeption(){
        super(MESSAGE);
    }

}
