package com.example.inventory.exception;

public class ProductAlreadyPresentException extends RuntimeException{

    public ProductAlreadyPresentException(){
        super();
    }

    public ProductAlreadyPresentException(Exception ex){
        super(ex);
    }

    public ProductAlreadyPresentException(String message){
        super(message);
    }
}
