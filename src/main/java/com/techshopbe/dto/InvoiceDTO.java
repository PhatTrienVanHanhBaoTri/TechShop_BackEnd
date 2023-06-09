package com.techshopbe.dto;

import lombok.*;

import java.util.List;

// hoa don
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {
	List<DetailedInvoiceDTO> detailedInvoices;
	private int userID;
	private String fullname;
	private String phone;
	private String address;
	private String email;
	private int totalPrice;
	private String note;
	private String statusInvoice;
	private String shippingDate;
	private String invoiceDate;
	private int couponID;
}
