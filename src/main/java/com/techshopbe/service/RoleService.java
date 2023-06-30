package com.techshopbe.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.Role;
import com.techshopbe.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository roleRepository;

	public Role getById(int id) {
		return roleRepository.findById(id).get();
	}
	public List<Role> getAll() {
		return roleRepository.findAll();
	}

}
