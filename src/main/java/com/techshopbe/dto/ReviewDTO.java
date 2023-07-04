package com.techshopbe.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
	private int reviewID;
	private int productID;
	private String fullname;
	private String reviewDate;
	private String reviewContent;
	private float rate;
}
