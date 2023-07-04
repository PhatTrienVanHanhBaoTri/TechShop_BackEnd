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
public class RatingInfoDTO {
	private float productRate;
	private int totalReviews;
}
