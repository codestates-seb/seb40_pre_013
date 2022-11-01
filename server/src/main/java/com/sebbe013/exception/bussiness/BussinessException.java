package com.sebbe013.exception.bussiness;

public abstract class  BussinessException extends RuntimeException {
    public BussinessException(){
    }
    public BussinessException( String message ){
        super(message);
    }

    public abstract int getCode();
}
