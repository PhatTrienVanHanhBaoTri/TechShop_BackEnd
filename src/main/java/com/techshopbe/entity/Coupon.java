package com.techshopbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COUPON")
public class Coupon {
    @Id
    private int ID;
    private int value;
    private String couponCode;
    private Date expiry;
    private CouponType couponType;
}
