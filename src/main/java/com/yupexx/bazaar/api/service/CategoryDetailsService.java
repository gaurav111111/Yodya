package com.yupexx.bazaar.api.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryDetailsModel;
import com.yupexx.bazaar.api.repository.CategoryDetailRepository;

import com.yupexx.bazaar.api.service.interfaces.CategoryDetailsInterface;

@Service
public class CategoryDetailsService implements CategoryDetailsInterface {
	
	CategoryDetailRepository dao;
	
	@Override
	public List<CategoryDetailsModel> getAllCatDetails() {
		// TODO Auto-generated method stub
//		
		return dao.findAll();
	}
	
	
	@Override
	public Optional<CategoryDetailsModel> getCatDetailsById(int id) {
		// TODO Auto-generated method stub
//
		return dao.findById(id);
	}

}
