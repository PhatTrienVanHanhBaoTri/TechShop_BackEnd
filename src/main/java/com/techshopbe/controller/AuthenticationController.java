package com.techshopbe.controller;

import java.util.Date;

import com.techshopbe.dto.*;
import com.techshopbe.entity.User;
import com.techshopbe.exception.OtpExpiredException;
import com.techshopbe.exception.OtpIncorrectException;
import com.techshopbe.exception.UserNotFoundException;
import com.techshopbe.security.JwtService;
import com.techshopbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final JwtService jwtService;

	@PostMapping("/login")
	public Object login(@RequestBody AuthenticationRequestDTO request) {
		try{
			Authentication authentication =  authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(),
							request.getPswd()
					)
			);

			User user = userService.getByEmail(request.getEmail());

			String jwtToken = jwtService.generateJwtToken(user);
			return ResponseEntity.ok(AuthenticationDTO.builder()
					.jwtToken(jwtToken)
					.userID(user.getUserID())
					.email(user.getEmail())
					.fullName(user.getFullname())
					.roleID(user.getRoleID())
					.build());
		}
		catch (AuthenticationException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( StringResponseDTO.builder()
					.message("Tài khoản hoăc mật khẩu không hợp lệ")
					.build());
		}
	}

	@PostMapping(value = "/register")
	public Object add(@RequestBody UserRegisterDTO userDTO) {
		try {
			userService.add(new User(userDTO));
			return new ResponseEntity<String>("Add Successfully!", HttpStatus.CREATED);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			if(!e.getMessage().equals("Email already existed")) {
				return new ResponseEntity<String>("Add Failed", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/forgotPassword/{userEmail}")
	public ResponseEntity<Object> forgotPassword(@PathVariable String userEmail){
		try {
			userService.forgotPassword(userEmail);
		}
		catch (UserNotFoundException usnfe){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponseDTO(usnfe.getMessage()));
		}
		return ResponseEntity.ok("Email sent!");
	}

	@PutMapping(value = "/resetPassword")
	public ResponseEntity<Object> forgotPassword(@RequestBody ResetPasswordDTO dto){
		try {
			userService.resetPassword(dto);
		}
		catch (UserNotFoundException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponseDTO(e.getMessage()));
		}
		catch (OtpIncorrectException | OtpExpiredException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringResponseDTO(e.getMessage()));
		}
		return ResponseEntity.ok("");
	}
}
