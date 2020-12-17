package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.view.SellerAdCountVwModel;
import com.yupexx.bazaar.api.repository.SellerAdCountVwRepository;
import com.yupexx.bazaar.api.repository.UserRepository;
import com.yupexx.bazaar.api.service.interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
    UserRepository dao;
	
	@Autowired
    SellerAdCountVwRepository daoSeller;

	@Override
	public List<UserModel> getUsers() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	@Override
	public List<SellerAdCountVwModel> getSimilarSellers() {
		// TODO Auto-generated method stub
		return daoSeller.findAll();
	}

	@Override
	public Optional getUserById(Long userId) {
		// TODO Auto-generated method stub
		return dao.findById(userId);
	}

	@Override
	public UserModel addUser(UserModel user) {
		// TODO Auto-generated method stub
		return user; // restricting of adding
		//return dao.save(user);
	}

	@Override
	public UserModel updateUser(UserModel user) {
		// TODO Auto-generated method stub
		//user.setPassword(null);
		return dao.save(user);
	}

	@Override
	public UserModel deleteUser(UserModel user) {
		// TODO Auto-generated method stub
		user.setStatus(false);
		return dao.save(user);
	}
	
	@Override
	public UserModel findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

}
