package com.sebbe013.exception.bussiness;

/*
중복 디스플레이 네임 예외 클래스
 */
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
