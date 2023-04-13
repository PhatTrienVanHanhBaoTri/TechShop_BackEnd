package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techshopbe.entity.Brand;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BrandRepository  extends JpaRepository<Brand, Integer> {

}
