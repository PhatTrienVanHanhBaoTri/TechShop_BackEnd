package com.techshopbe.exception;

public class InvalidException extends RuntimeException{
    public InvalidException(String message) {
        super(message);
    }
    public InvalidException(){
        super("Invalid Request");
    }
}
