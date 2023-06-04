package com.techshopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedProductDTO {
	private int productID;
	private int categoryID;
	private int brandID;
	private String categoryName;
	private String brandName;
	private float productRate;
	private String productName;
	private int productPrice;
	private String shortDescrip;
	private String longDescrip;
	private int stock;
	private int warranty;
	private int purchased;
	private String specName1;
	private String spec1;
	private String specName2;
	private String spec2;
	private String specName3;
	private String spec3;
	private String specName4;
	private String spec4;
	private String stockStatus;
	private String shortTech;
	private int totalReviews;
	private String images;

	public DetailedProductDTO(int productID, int categoryID, int brandID, String categoryName, String brandName, float productRate,
			String productName, int productPrice, String shortDescrip, String longDescrip, int stock, int warranty,
			int purchased, String specName1, String spec1, String specName2, String spec2, String specName3, String spec3, String specName4, String spec4,
		  	String shortTech, int totalReviews, String images) {
		super();
		this.productID = productID;
		this.categoryID = categoryID;
		this.brandID = brandID;
		this.categoryName = categoryName;
		this.brandName = brandName;
		this.productRate = productRate;
		this.productName = productName;
		this.productPrice = productPrice;
		this.shortDescrip = shortDescrip;
		this.longDescrip = longDescrip;
		this.stock = stock;
		this.warranty = warranty;
		this.purchased = purchased;
		this.specName1 = specName1;
		this.spec1 = spec1;
		this.specName2 = specName2;
		this.spec2 = spec2;
		this.specName3 = specName3;
		this.spec3 = spec3;
		this.specName4 = specName4;
		this.spec4 = spec4;
		this.shortTech = shortTech;
		this.totalReviews = totalReviews;
		this.images = images;
	}

}
