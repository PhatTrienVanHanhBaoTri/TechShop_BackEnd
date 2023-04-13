package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.Invoice;
import com.techshopbe.entity.User;

@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	
	@Query("SELECT invoiceID FROM Invoice i WHERE i.userInvoiceIndex = ?1")
	int findInvoiceIDByUserInvoiceIndex(String userInvoiceIndex);
	
	List<Invoice> findByUserID(int userID);
	Invoice findByInvoiceID(int invoiceID);
}
