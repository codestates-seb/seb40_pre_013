package com.sebbe013.exception.bussiness;

/*
비지니스 예외를 효율적으로 처리하기 위한 추상 클래스.
 */

public abstract class  BussinessException extends RuntimeException {
    public BussinessException( String message ){
        super(message);
    }
    public abstract int getCode();
}
