package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
