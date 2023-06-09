package com.techshopbe.dto;

import lombok.*;
import org.springframework.stereotype.Service;

// chi tiet hoa don
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailedInvoiceDTO {
	private int productID;
	private int productPrice;
	private int quantity;
	private int totalPrice;
	private String productName;
	private boolean isReviewed;
	private String images;
	private String categorySlug;
}
