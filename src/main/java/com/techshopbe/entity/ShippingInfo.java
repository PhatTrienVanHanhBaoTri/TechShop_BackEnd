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
@Table(name = "SHIPPINGINFO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shippingInfoID;
	private int invoiceID;
	private String fullname;
	private String phone;
	private String address;
}
