package com.techshopbe.exception;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(){
        super("Resource not found");
    }
}
