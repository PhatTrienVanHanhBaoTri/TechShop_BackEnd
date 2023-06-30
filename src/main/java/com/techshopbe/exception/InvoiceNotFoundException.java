package com.techshopbe.exception;

import java.util.NoSuchElementException;

public class InvoiceNotFoundException extends NotFoundException {
    public InvoiceNotFoundException(String message) {
        super(message);
    }
    public InvoiceNotFoundException(){
        super("Invoice does not exist");
    }
}
