package com.example.inventory.exception;

public class InventoryCountMismatchException extends RuntimeException{

    public InventoryCountMismatchException(){
        super();
    }

    public InventoryCountMismatchException(Exception ex){
        super(ex);
    }

    public InventoryCountMismatchException(String message){
        super(message);
    }
}
