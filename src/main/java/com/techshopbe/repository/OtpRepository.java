package com.techshopbe.repository;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.entity.OTP;
import com.techshopbe.entity.constant.OTP_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OtpRepository extends JpaRepository<OTP, Integer> {
    @Query(
            value = "SELECT * " +
                    "FROM OTP o " +
                    "WHERE o.recipient_email = ?1 " +
                    "AND o.otp_type = ?2 " +
                    "ORDER BY o.date DESC",
            nativeQuery = true
    )
    List<OTP> findOTPByRecipientEmailAndAndOtpTypeOrderByDateDesc(String recipientEmail, int type);
}
