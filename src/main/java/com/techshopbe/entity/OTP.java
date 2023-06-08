package com.techshopbe.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "OTP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String recipientEmail;
    private Date date;
    private Date expiry;
    private String OTP;
    private String otpType;

    public OTP(String recipientEmail, Date date, Date expiry, String otp, String otpType) {
        this.recipientEmail = recipientEmail;
        this.date = date;
        this.expiry = expiry;
        this.OTP = otp;
        this.otpType = otpType;
    }
}
