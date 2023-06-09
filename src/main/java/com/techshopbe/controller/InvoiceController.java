package com.techshopbe.controller;

import java.util.List;

import com.techshopbe.dto.StringResponseDTO;
import com.techshopbe.exception.InvoiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.techshopbe.service.InvoiceService;

@RestController
@RequestMapping("api/v1/invoice")
@Slf4j
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	@PostMapping(value = "")
	public Object add(@RequestBody InvoiceDTO invoice) {
		try {
			invoiceService.add(invoice);
			return ResponseEntity.status(HttpStatus.CREATED).body(new StringResponseDTO("Order Successfully"));
		} catch(Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringResponseDTO("Order Failed"));
		}
	}
	@GetMapping(value = "")
	public Object getAllInvoices() {

		try {
			List<Invoice> userInvoices = invoiceService.getAllInvoices();
			return new ResponseEntity<List<Invoice>>(userInvoices, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/user")
	public Object getAllUserInvoices() {
		
		try {
			List<Invoice> userInvoices = invoiceService.getAllUserInvoices();
			return new ResponseEntity<List<Invoice>>(userInvoices, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/{invoiceID}")
	public Object getByInvoiceID(@PathVariable int invoiceID) {
		
		try {
			InvoiceDTO invoice = invoiceService.getByInvoiceID(invoiceID);
			return new ResponseEntity<InvoiceDTO>(invoice, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{couponID}/user/{userID}")
	public ResponseEntity<Object> getCouponByIDAndUserID(@PathVariable int couponID, @PathVariable int userID){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(invoiceService.getByCouponIDAndUserID(couponID, userID));
		}
		catch (InvoiceNotFoundException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponseDTO(e.getMessage()));
		}
		catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}
}
