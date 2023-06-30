package com.techshopbe.controller;

import java.util.List;

import com.techshopbe.service.InvoiceService;
import com.techshopbe.service.ProductService;
import com.techshopbe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.PostReviewDTO;
import com.techshopbe.dto.ReviewDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/review")
public class ReviewController {
	private final ReviewService reviewService;
	private final ProductService productService;
	private final InvoiceService invoiceService;

	@GetMapping(value = "/{productID}")
	public Object getAllReviewsByProductID(@PathVariable int productID, @RequestParam int limit, @RequestParam int page) {
		//System.out.println(page);
		List<ReviewDTO> reviewsByProductID = reviewService.getAllReviewsByProductID(productID, PageRequest.of(page,  limit));
		return ResponseEntity.ok().body(reviewsByProductID);
	}
	
	@PostMapping(value = "")
	public Object addReview(@RequestBody PostReviewDTO postReviewDTO) {
		// add review
		reviewService.addReview(postReviewDTO);

		// recalculate rate in product table
		productService.updateRating(postReviewDTO.getProductID(), postReviewDTO.getRate());

		// update isReviewed in DetailedInvoice
		invoiceService.updateReviewStatus(postReviewDTO.getOrderID(), postReviewDTO.getProductID());

		return ResponseEntity.status(HttpStatus.CREATED);
	}

}
