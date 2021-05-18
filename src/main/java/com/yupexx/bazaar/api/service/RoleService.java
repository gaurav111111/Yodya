package com.yupexx.bazaar.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.RoleModel;
import com.yupexx.bazaar.api.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository role;

	public Optional<RoleModel> findRole(Integer roleType) {
		// TODO Auto-generated method stub
		
		return role.findById(roleType);
	}
}
