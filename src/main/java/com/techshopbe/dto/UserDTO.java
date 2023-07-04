package com.techshopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private int userID;
	private String fullname;
	private String phone;
	private String address;
	private String email;
	private String gender;
	private String dob;
}
