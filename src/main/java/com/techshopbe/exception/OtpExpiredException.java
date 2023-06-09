package com.techshopbe.exception;

public class OtpExpiredException extends Exception{
    public OtpExpiredException(String message) {
        super(message);
    }
    public OtpExpiredException(){
        super("OTP has expired");
    }
}
