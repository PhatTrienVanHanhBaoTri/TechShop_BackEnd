package com.techshopbe.controller;

import java.util.List;

import com.techshopbe.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.entity.Brand;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/brand")
@Slf4j
public class BrandController {
	private final BrandService brandService;

	@GetMapping(value = "")
	public Object index() {
		List<Brand> brandList = brandService.getAll();
		return new ResponseEntity<List<Brand>>(brandList, HttpStatus.OK);
	}
}
