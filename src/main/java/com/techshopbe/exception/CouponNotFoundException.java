package com.techshopbe.exception;

import java.util.NoSuchElementException;

public class CouponNotFoundException extends NoSuchElementException {
    public CouponNotFoundException(String message) {
        super(message);
    }
    public CouponNotFoundException(){
        super("Coupon does not exist");
    }
}
