package com.techshopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private int productID;
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
	private String categorySlug;
	private String images;

}
