package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.ConfirmEmailDTO;
import com.techshopbe.dto.ResetPasswordDTO;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.entity.User;
import com.techshopbe.exception.OtpExpiredException;
import com.techshopbe.exception.OtpIncorrectException;

public interface UserService {
	List<User> getAll();
	User getById(int id);
	User getByEmail(String email);
	void add(User user) throws Exception;
	void delete(int id);
	ShippingInfoDTO getShippingInfoByEmail(String email);
	void forgotPassword(String userEmail);
    void resetPassword(ResetPasswordDTO dto) throws OtpIncorrectException, OtpExpiredException;
	void validateOTPConfirmEmail(ConfirmEmailDTO dto) throws OtpIncorrectException, OtpExpiredException;
}
