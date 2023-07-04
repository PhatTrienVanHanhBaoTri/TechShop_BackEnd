package com.techshopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReviewDTO {
	private int orderID;
	private int productID;
	private String reviewContent;
	private float rate;
}
