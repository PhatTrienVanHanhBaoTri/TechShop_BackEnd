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
@Table(name = "REVIEW")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewID;
	private int productID;
	private int userID;
	private String reviewDate;
	private String reviewContent;
	private float rate;
}
