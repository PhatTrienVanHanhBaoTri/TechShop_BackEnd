package com.techshopbe.controller;

import java.util.List;

import com.techshopbe.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techshopbe.entity.Category;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping(value = "")
	public Object index() {
		List<Category> categoryList = categoryService.getAll();
		return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
	}
}
