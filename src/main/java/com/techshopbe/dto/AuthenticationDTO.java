package com.techshopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
	private String email;
	private int userID;
	private int roleID;
	private String fullName;
	private String jwtToken;
}
