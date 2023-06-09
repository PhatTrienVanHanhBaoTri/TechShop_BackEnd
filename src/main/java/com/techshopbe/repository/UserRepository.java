package com.techshopbe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	@Query("SELECT new com.techshopbe.dto.ShippingInfoDTO(u.fullname, u.phone, u.address) FROM User u WHERE u.email = ?1")
	ShippingInfoDTO findShippingInfoByEmail(String email);
	
	@Query("SELECT u.totalInvoices FROM User u WHERE u.email = ?1")
	int findTotalInvoicesByEmail(String email);

	@Modifying
    @Query("UPDATE User u SET u.totalInvoices = ?1 WHERE u.email = ?2")
	void updateTotalInvoicesByEmail(int totalInvoices, String email);

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.pswd = ?1 WHERE u.email = ?2")
	void resetPassword(String newPassword, String userEmail);
}
