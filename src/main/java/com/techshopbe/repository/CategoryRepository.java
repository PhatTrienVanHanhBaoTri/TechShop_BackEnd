package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.Category;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
