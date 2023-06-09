package com.techshopbe.service.impl;

import com.techshopbe.dto.CouponDTO;
import com.techshopbe.entity.Coupon;
import com.techshopbe.entity.Invoice;
import com.techshopbe.exception.CouponNotFoundException;
import com.techshopbe.repository.CouponRepository;
import com.techshopbe.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
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

    public Coupon updateCoupon(int couponID, CouponDTO dto) throws ParseException {
        Coupon coupon = findCouponByID(couponID);
        coupon.setValue(dto.getValue());
        coupon.setExpiry(stringToDate(dto.getExpiry()));
        coupon.setCouponType(dto.getCouponType());
        couponRepository.save(coupon);
        return couponRepository.findCouponByID(couponID).orElse(null);
    }

    private Date stringToDate(String dob) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dob);
    }

    public void deleteCoupon(int couponID) {
        log.trace("deleted Coupon ID: " + couponID);
        couponRepository.deleteById(couponID);
        log.trace("deleted Coupon ID: " + couponID);
    }
    public List<Coupon> findAllCoupon(){
        return couponRepository.findAll();
    }
}
