package com.techshopbe.repository;

import com.techshopbe.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OTP, Integer> {
}
