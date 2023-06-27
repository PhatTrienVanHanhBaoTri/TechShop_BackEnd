package com.techshopbe.repository;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.entity.OTP;
import com.techshopbe.entity.constant.OTP_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OtpRepository extends JpaRepository<OTP, Integer> {
    List<OTP> findOTPByRecipientEmailAndAndOtpTypeOrderByDateDesc(@Param("recipientEmail") String recipientEmail,@Param("otpType") OTP_Type type);
}
