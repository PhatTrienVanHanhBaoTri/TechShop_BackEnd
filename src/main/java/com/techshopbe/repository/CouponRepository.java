package com.techshopbe.repository;

import com.techshopbe.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Optional<Coupon> findCouponByID(Integer ID);
    Optional<Coupon> findCouponByCouponCode(String couponCode);
}
