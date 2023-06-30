package com.techshopbe.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.Brand;
import com.techshopbe.repository.BrandRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {
	private final BrandRepository brandRepository;

	public List<Brand> getAll() {
		return brandRepository.findAll();
	}

}
