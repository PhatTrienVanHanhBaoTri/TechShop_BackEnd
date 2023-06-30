package com.techshopbe.dto;

import com.techshopbe.entity.Coupon;
import com.techshopbe.entity.constant.CouponType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO {
    private static final int CODE_LENGTH = 6;
    private int value;
    private String otp;
    private String expiry;
    private CouponType couponType;

    public Coupon convertToCoupon() throws ParseException {
        Coupon coupon = new Coupon();
        coupon.setExpiry(stringToDate(this.getExpiry()));
        coupon.setCouponType(this.getCouponType());
        coupon.setValue(this.getValue());
        coupon.setCouponCode(this.getOtp() == null ? createRandomCode() : this.getOtp());

        return coupon;
    }

    private String createRandomCode(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < CODE_LENGTH; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output ;
    }

    private Date stringToDate(String dob) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dob);
    }
}
