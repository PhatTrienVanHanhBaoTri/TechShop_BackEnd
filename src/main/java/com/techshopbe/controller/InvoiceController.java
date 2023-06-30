package com.techshopbe.controller;

import java.util.List;

import com.techshopbe.dto.StringResponseDTO;
import com.techshopbe.exception.InvoiceNotFoundException;
import com.techshopbe.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.entity.Invoice;

@RestController
@RequestMapping("api/v1/invoice")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController {
	private final InvoiceService invoiceService;
	@PostMapping(value = "")
	public Object add(@RequestBody InvoiceDTO invoice) {
		invoiceService.add(invoice);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StringResponseDTO("Order Successfully"));
	}
	@GetMapping(value = "")
	public Object getAllInvoices() {
		List<Invoice> userInvoices = invoiceService.getAllInvoices();
		return new ResponseEntity<List<Invoice>>(userInvoices, HttpStatus.OK);
	}
	@GetMapping(value = "/user/{userID}")
	public Object getAllUserInvoices(@PathVariable int userID) {
		List<Invoice> userInvoices = invoiceService.getAllUserInvoices(userID);
		return new ResponseEntity<List<Invoice>>(userInvoices, HttpStatus.OK);
	}
	@GetMapping(value = "/{invoiceID}")
	public Object getByInvoiceID(@PathVariable int invoiceID) {
		InvoiceDTO invoice = invoiceService.getByInvoiceID(invoiceID);
		return new ResponseEntity<InvoiceDTO>(invoice, HttpStatus.OK);
	}

	@GetMapping(value = "/{couponID}/user/{userID}")
	public ResponseEntity<Object> getCouponByIDAndUserID(@PathVariable int couponID, @PathVariable int userID){
		return ResponseEntity.status(HttpStatus.OK).body(invoiceService.getByCouponIDAndUserID(couponID, userID));
	}
}
