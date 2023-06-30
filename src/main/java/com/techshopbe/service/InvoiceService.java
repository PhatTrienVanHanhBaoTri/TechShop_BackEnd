package com.techshopbe.service;

import java.time.LocalDateTime;
import java.util.List;

import com.techshopbe.entity.*;
import com.techshopbe.entity.constant.CouponType;
import com.techshopbe.exception.CouponNotFoundException;
import com.techshopbe.exception.InvoiceNotFoundException;
import com.techshopbe.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.DetailedInvoiceDTO;
import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.repository.DetailedInvoiceRepository;
import com.techshopbe.repository.InvoiceRepository;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.repository.ShippingInfoRepository;
import com.techshopbe.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class InvoiceService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final InvoiceRepository invoiceRepository;
	private final ShippingInfoRepository shippingInfoRepository;
	private final DetailedInvoiceRepository detailedInvoiceRepository;
	private final CouponService couponService;
	public void add(InvoiceDTO invoiceDTO) {
		boolean otherShippingAddress = true;

		String email = invoiceDTO.getEmail();
//		invoiceDTO.setEmail(email);

		// set detailed invoice (calculate price and total price)
		invoiceDTO.setDetailedInvoices(getDetailedInvoices(invoiceDTO.getDetailedInvoices()));

		// set shipping info
		if (invoiceDTO.getAddress() == null) {
			ShippingInfoDTO shippingInfo = getShippingInfo(email);
			invoiceDTO.setAddress(shippingInfo.getAddress());
			invoiceDTO.setPhone(shippingInfo.getPhone());
			invoiceDTO.setFullname(shippingInfo.getFullname());
			otherShippingAddress = false;
		}

		// set total price of invoice
		invoiceDTO.setTotalPrice(calculateTotalInvoiceCost(invoiceDTO.getDetailedInvoices(), invoiceDTO.getCouponID()));

		/*
		 * Insert new invoice
		 */
		// INVOICE userID, totalCost, invoiceDate, shippingDate, note,
		// otherShippingAddress, statusInvoice
		// shipping Date: now + 10 ngày
		int userID = invoiceDTO.getUserID();
		LocalDateTime invoiceDate = LocalDateTime.now();
		LocalDateTime shippingDate = invoiceDate.plusDays(3);

		// totalInvoices: lưu tổng số hoá đơn của người dùng
		// userInvoiceIndex: là key phân biệt các invoice (không lấy newest id)
		// (userInvoiceIndex = email + totalInvoices)
		int totalInvoices = userRepository.findTotalInvoicesByEmail(email);
		String userInvoiceIndex = email + String.valueOf(totalInvoices);

		Invoice invoiceEntity = new Invoice();
		invoiceEntity.setUserID(userID);
		invoiceEntity.setTotalCost(invoiceDTO.getTotalPrice());
		invoiceEntity.setInvoiceDate(invoiceDate.toString());
		invoiceEntity.setShippingDate(shippingDate.toString());
		invoiceEntity.setNote(invoiceDTO.getNote());
		invoiceEntity.setOtherShippingAddress(otherShippingAddress);
		invoiceEntity.setUserInvoiceIndex(userInvoiceIndex);
		invoiceEntity.setCouponID(invoiceDTO.getCouponID());

		invoiceEntity = invoiceRepository.save(invoiceEntity);
		// after insert invoice, increase totalInvoices of user
		userRepository.updateTotalInvoicesByEmail(totalInvoices + 1, email);

		/*
		 * Insert new shipping
		 */
		// get current invoice ID through userInvoiceIndex
		int invoiceID = invoiceRepository.findInvoiceIDByUserInvoiceIndex(userInvoiceIndex);

		// SHIPPINGINFO invoiceID, fullname, phone, address
		ShippingInfo shippingInfo = new ShippingInfo();
		shippingInfo.setInvoiceID(invoiceID);
		shippingInfo.setAddress(invoiceDTO.getAddress());
		shippingInfo.setFullname(invoiceDTO.getFullname());
		shippingInfo.setPhone(invoiceDTO.getPhone());
		shippingInfoRepository.save(shippingInfo);

		/*
		 * Insert new DETAILED INVOICE
		 */

		// DETAILEDINVOICE (invoiceID, productID, quantity, price)
		for (DetailedInvoiceDTO detailedInvoiceDTO : invoiceDTO.getDetailedInvoices()) {
			DetailedInvoice detailedInvoice = new DetailedInvoice();
			detailedInvoice.setInvoiceID(invoiceID);
			detailedInvoice.setPrice(detailedInvoiceDTO.getProductPrice());
			detailedInvoice.setProductID(detailedInvoiceDTO.getProductID());
			detailedInvoice.setQuantity(detailedInvoiceDTO.getQuantity());
			detailedInvoice.setTotalPrice(detailedInvoiceDTO.getTotalPrice());
			detailedInvoiceRepository.save(detailedInvoice);
		}
	}

	public List<DetailedInvoiceDTO> getDetailedInvoices(List<DetailedInvoiceDTO> detailedInvoices) {

		for (DetailedInvoiceDTO detailedInvoice : detailedInvoices) {
			// System.out.println(detailedInvoice.getProductID());
			int price = productRepository.findProductPriceByProductID(detailedInvoice.getProductID());

			int totalPrice = price * detailedInvoice.getQuantity();

			detailedInvoice.setProductPrice(price);
			detailedInvoice.setTotalPrice(totalPrice);
		}
		return detailedInvoices;
	}

	public ShippingInfoDTO getShippingInfo(String email) {
		return userRepository.findShippingInfoByEmail(email);
	}

	public int calculateTotalInvoiceCost(List<DetailedInvoiceDTO> detailedInvoices, int couponID) {
		int totalInvoiceCost = 0;
		for (DetailedInvoiceDTO detailedInvoice : detailedInvoices) {
			totalInvoiceCost += detailedInvoice.getTotalPrice();
		}
		try {
			Coupon coupon = couponService.findCouponByID(couponID);
			return coupon.getCouponType() == CouponType.MONEY ? (totalInvoiceCost - coupon.getValue()) : totalInvoiceCost - Math.round((float)totalInvoiceCost * (float)coupon.getValue() / 100);
		}
		catch (CouponNotFoundException cnfe){
			return totalInvoiceCost;
		}
	}

	public List<Invoice> getAllUserInvoices(int userID) {
		return invoiceRepository.findByUserID(userID);
	}

	public InvoiceDTO getByInvoiceID(int invoiceID) {
		List<DetailedInvoiceDTO> detailedInvoices = detailedInvoiceRepository.findAllByInvoiceID(invoiceID);
		ShippingInfoDTO shippingInfo = shippingInfoRepository.findByInvoiceID(invoiceID);
		Invoice invoice = invoiceRepository.findByInvoiceID(invoiceID);

		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setDetailedInvoices(detailedInvoices);
		invoiceDTO.setNote(invoice.getNote());
		invoiceDTO.setAddress(shippingInfo.getAddress());
		invoiceDTO.setPhone(shippingInfo.getPhone());
		invoiceDTO.setFullname(shippingInfo.getFullname());
		invoiceDTO.setStatusInvoice(invoice.getStatusInvoice());
		invoiceDTO.setTotalPrice(invoice.getTotalCost());
		invoiceDTO.setShippingDate(invoice.getShippingDate());
		invoiceDTO.setInvoiceDate(invoice.getInvoiceDate());

		return invoiceDTO;
	}

	public void updateReviewStatus(int invoiceID, int productID) {
		detailedInvoiceRepository.updateRatingInfoByProductID(invoiceID, productID);
	}
	public Invoice getByCouponIDAndUserID(int couponID, int userID) {
		return invoiceRepository.findByUserIDAndCouponID(userID, couponID).orElseThrow(InvoiceNotFoundException::new);
	}

	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}
}
