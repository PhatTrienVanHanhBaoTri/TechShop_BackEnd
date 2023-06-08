package com.techshopbe.exception;

public class OtpIncorrectException extends Exception{
    public OtpIncorrectException(String message) {
        super(message);
    }
    public OtpIncorrectException(){
        super("OTP không trùng khớp vui lòng kiểm tra lại.");
    }
}
