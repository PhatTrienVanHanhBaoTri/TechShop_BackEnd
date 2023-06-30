package com.techshopbe.exception;

public class OtpIncorrectException extends InvalidException{
    public OtpIncorrectException(String message) {
        super(message);
    }
    public OtpIncorrectException(){
        super("OTP is incorrect");
    }
}
