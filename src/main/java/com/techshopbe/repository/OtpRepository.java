package com.techshopbe.repository;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OtpRepository extends JpaRepository<OTP, Integer> {
    List<OTP> findOTPByRecipientEmailOrderByDateDesc(String recipientEmail);
}
