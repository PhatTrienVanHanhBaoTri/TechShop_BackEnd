package com.techshopbe.entity;

import com.techshopbe.entity.constant.OTP_Type;
import jakarta.persistence.*;
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
    @Column(name = "id")
    private Integer id;
    @Column(name = "recipient_email")
    private String recipientEmail;
    @Column(name = "date")
    private Date date;
    @Column(name = "expiry")
    private Date expiry;
    @Column(name = "otp")
    private String OTP;
    @Column(name = "otp_type")
    private int otpType;

    public OTP(String recipientEmail, Date date, Date expiry, String otp, int otpType) {
        this.recipientEmail = recipientEmail;
        this.date = date;
        this.expiry = expiry;
        this.OTP = otp;
        this.otpType = otpType;
    }
}
