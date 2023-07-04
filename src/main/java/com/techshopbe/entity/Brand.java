package com.techshopbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BRAND")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int brandID;
	private String brandName;
	private String brandImg;
}
