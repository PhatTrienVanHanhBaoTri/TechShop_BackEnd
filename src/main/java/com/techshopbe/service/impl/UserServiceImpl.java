package com.techshopbe.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.techshopbe.dto.ResetPasswordDTO;
import com.techshopbe.entity.OTP;
import com.techshopbe.entity.OTP_Type;
import com.techshopbe.exception.OtpExpiredException;
import com.techshopbe.exception.OtpIncorrectException;
import com.techshopbe.exception.UserNotFoundException;
import com.techshopbe.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.entity.User;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
	private static final long OTP_EXPIRATION_TIME = 1000 * 60 * 60; //1 hour
	private static final String OTP_FORMAT = "000000";
	private final UserRepository userRepository;
	private final EmailService emailService;
	private final OtpRepository otpRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getById(int id) {
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public User getByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserNotFoundException();
		return user;
	}

	@Override
	public void add(User user) throws Exception {
		User entityHasSameEmail = userRepository.findByEmail(user.getEmail());
		if (entityHasSameEmail == null) {
			String hashPassword = BCrypt.hashpw(user.getPswd(), BCrypt.gensalt());
			user.setPswd(hashPassword);
			userRepository.save(user);
		} else {
			throw new Exception("Email already existed");
		}
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public ShippingInfoDTO getShippingInfoByEmail(String email) {
		User user = userRepository.findByEmail(email);
		ShippingInfoDTO shippingInfoDTO = new ShippingInfoDTO(user.getFullname(), user.getPhone(), user.getAddress());

		return shippingInfoDTO;
	}
	@Override
	public void forgotPassword(String userEmail) {
		User user = userRepository.findByEmail(userEmail);
		if (user == null){
			throw new UserNotFoundException();
		}

		String otp = generateOTP();

		emailService.sendMail(user.getEmail(), "OTP để đặt lại mật khẩu của bạn là: " + otp, "OTP quên mật khẩu");

		saveOtpToDatabase(new OTP(
				user.getEmail(),
				new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis() + OTP_EXPIRATION_TIME),
				otp,
				OTP_Type.RESET_PASSWORD.toString()));
	}

	@Override
	public void resetPassword(ResetPasswordDTO dto) throws OtpIncorrectException, OtpExpiredException {
		List<OTP> otpList = otpRepository.findOTPByRecipientEmailOrderByDateDesc(dto.getUserEmail());
		if (otpList.size() == 0)
			throw new UserNotFoundException();
		OTP recentOTP = otpList.get(0);

		if (!recentOTP.getOTP().equals(dto.getOTP()))
			throw new OtpIncorrectException();

		if (recentOTP.getExpiry().before(new Date(System.currentTimeMillis())))
			throw new OtpExpiredException();

		userRepository.resetPassword(passwordEncoder.encode(dto.getNewPassword()), dto.getUserEmail());
	}

	private void saveOtpToDatabase(OTP otp){
		otpRepository.save(otp);
		log.trace("New otp of user: " + otp.getRecipientEmail() + " has been stored to database");
	}

	private String generateOTP() {
		return new DecimalFormat(OTP_FORMAT).format(new Random().nextInt(999999));
	}
}
