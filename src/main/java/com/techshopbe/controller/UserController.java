package com.techshopbe.controller;

import java.io.Console;
import java.util.List;

import com.techshopbe.dto.StringResponseDTO;
import com.techshopbe.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.entity.User;
import com.techshopbe.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping(value = "")
	public Object index() {
		try {
			List<User> userList = userService.getAll();
			if(userList.isEmpty())
				return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/shippingInfo/{email}")
	public Object getShippingInfo(@PathVariable String email) {
		try {
			
			ShippingInfoDTO shippingInfoDTO = userService.getShippingInfoByEmail(email);
			
			return new ResponseEntity<ShippingInfoDTO>(shippingInfoDTO, HttpStatus.OK);
				
		} catch(Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping(value = "/{id}")
	public Object delete(@PathVariable int id) {
		try {
			userService.delete(id);
			return new ResponseEntity<String>("Delete Successfully!", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Delete Failed", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/email/{email}")
	public Object getUserByEmail(@PathVariable String email) {
		try {

			User user = userService.getByEmail(email);
			return ResponseEntity.ok(user);
		} catch(UserNotFoundException unfe) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponseDTO(unfe.getMessage()));
		}
	}
	@GetMapping(value = "/id/{id}")
	public Object getUserByID(@PathVariable int id) {
		try {
			User user = userService.getById(id);
			return ResponseEntity.ok(user);
		} catch(UserNotFoundException unfe) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponseDTO(unfe.getMessage()));
		}
	}
}
