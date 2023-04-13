package com.techshopbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BRAND")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int brandID;
	private String brandName;
	private String brandImg;
	public Brand() {
	}

	public Brand(int brandID, String brandName, String brandImg) {
		super();
		this.brandID = brandID;
		this.brandName = brandName;
		this.brandImg = brandImg;
	}


	public int getBrandID() {
		return brandID;
	}


	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public String getBrandImg() {
		return brandImg;
	}


	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}



}
