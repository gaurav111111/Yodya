package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryAccessoryModel;
import com.yupexx.bazaar.api.repository.CategoryAccessoryRepository;
import com.yupexx.bazaar.api.service.interfaces.CategoryAccessoryInterface;

@Service
public class CategoryAccessoryService implements CategoryAccessoryInterface {

	@Autowired
	CategoryAccessoryRepository dao;
	
	@Override
	public List<CategoryAccessoryModel> getAllCategoryAccessorys() {
		// TODO Auto-generated method stub
		return dao.findByStatus(true);
	}

	@Override
	public Optional<CategoryAccessoryModel> getCategoryAccessoryById(Integer objectclassId) {
		// TODO Auto-generated method stub
		return dao.findByIdAndStatus(objectclassId,true);
	}

	@Override
	public CategoryAccessoryModel addNewCategoryAccessory(CategoryAccessoryModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public CategoryAccessoryModel updateCategoryAccessory(Integer objectclassId,CategoryAccessoryModel object) {
		// TODO Auto-generated method stub
		CategoryAccessoryModel objectclass = dao.findById(objectclassId).orElseThrow(() -> new EntityNotFoundException());
		objectclass.setAccessoryName(object.getAccessoryName());
		return dao.save(objectclass);
	}
	
	@Override
	public CategoryAccessoryModel deleteCategoryAccessory(Integer objectclassId) {
		// TODO Auto-generated method stub
		CategoryAccessoryModel object = dao.findById(objectclassId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
