package com.techshopbe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	@Query("SELECT new com.techshopbe.dto.ShippingInfoDTO(u.fullname, u.phone, u.address) FROM User u WHERE u.email = ?1")
	ShippingInfoDTO findShippingInfoByEmail(String email);
	
	@Query("SELECT totalInvoices FROM User u WHERE u.email = ?1")
	int findTotalInvoicesByEmail(String email);

	@Modifying
    @Query("UPDATE User u SET u.totalInvoices = ?1 WHERE u.email = ?2")
    int updateTotalInvoicesByEmail(int totalInvoices, String email);
}
