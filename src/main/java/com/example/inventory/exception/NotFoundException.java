package com.example.inventory.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super();
    }

    public NotFoundException(Exception ex){
        super(ex);
    }

    public NotFoundException(String message){
        super(message);
    }
}
