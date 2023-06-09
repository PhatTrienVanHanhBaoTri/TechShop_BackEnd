package com.techshopbe.exception;


import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(){
        super("User does not exist");
    }
}
