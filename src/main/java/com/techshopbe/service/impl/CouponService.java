package com.techshopbe.service.impl;

import com.techshopbe.dto.CouponDTO;
import com.techshopbe.entity.Coupon;
import com.techshopbe.exception.CouponNotFoundException;
import com.techshopbe.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public Coupon findCouponByID(int id){
        return couponRepository.findCouponByID(id).orElseThrow(CouponNotFoundException::new);
    }

    public Coupon addCoupon(CouponDTO dto) throws ParseException {
        Coupon coupon = dto.convertToCoupon(dto);
        couponRepository.save(coupon);
        return couponRepository.findCouponByCouponCode(coupon.getCouponCode()).orElse(null);
    }
}
