package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.entity.Invoice;

public interface InvoiceService {
	public void add(InvoiceDTO invoice);
	public List<Invoice> getAllUserInvoices(int userID);
	public InvoiceDTO getByInvoiceID(int invoiceID);
	public void updateReviewStatus(int invoiceID, int productID);
	public Invoice getByCouponIDAndUserID(int couponID, int userID);
	public List<Invoice> getAllInvoices();
}
