package com.sebbe013.exception.bussiness;
/*
이메일 중복 예외 처리
 */
public class ExistEamilException extends BussinessException {
    private static final String MESSAGE = ExceptionCode.EXIST_EMAIL.getMessage(); //enum에서 메시지를 받아온다.
    public ExistEamilException(){ //메세지만
        super(MESSAGE);
    } // 예외가 발생했을 시 메시지
    @Override
    public int getCode(){
        return ExceptionCode.EXIST_EMAIL.getCode();
    } // enum에서 상태코드를 받아온다.
}
