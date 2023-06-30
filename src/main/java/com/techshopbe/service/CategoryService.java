package com.techshopbe.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.Category;
import com.techshopbe.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

}
