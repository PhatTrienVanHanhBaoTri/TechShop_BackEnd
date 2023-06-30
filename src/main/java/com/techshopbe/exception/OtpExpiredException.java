package com.techshopbe.exception;

public class OtpExpiredException extends InvalidException{
    public OtpExpiredException(String message) {
        super(message);
    }
    public OtpExpiredException(){
        super("OTP has expired");
    }
}
