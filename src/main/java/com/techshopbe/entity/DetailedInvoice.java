package com.techshopbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DETAILEDINVOICE")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailedInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detailedInvoiceID;
	private int invoiceID;
	private int productID;
	private int quantity;
	private int price;
	private int totalPrice;
	private boolean isReviewed;
}
