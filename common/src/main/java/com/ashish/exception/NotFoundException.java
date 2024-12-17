package com.ashish.exception;

public class NotFoundException extends RuntimeException {

    private  String value;

    private Class<?> clazz;

    private String field;

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(Class<?> clazz, String field, Object value){
        super(String.format("cannot find %s with the field %s and value %s", clazz, field, value.toString()));
    }


}
