package com.techshopbe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryID;
	private String categoryName;
	private String categorySlug;
	private boolean categoryExact;
}
