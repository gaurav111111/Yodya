package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryProductTypeModel;
import com.yupexx.bazaar.api.repository.CategoryProductTypeRepository;
import com.yupexx.bazaar.api.service.interfaces.CategoryProductTypeInterface;

@Service
public class CategoryProductTypeService implements CategoryProductTypeInterface {

	@Autowired
	CategoryProductTypeRepository dao;
	
	@Override
	public List<CategoryProductTypeModel> getAllCategoryProductTypes() {
		// TODO Auto-generated method stub
		return dao.findByStatus(true);
	}

	@Override
	public Optional<CategoryProductTypeModel> getCategoryProductTypeById(Integer blogId) {
		// TODO Auto-generated method stub
		return dao.findByIdAndStatus(blogId,true);
	}

	@Override
	public CategoryProductTypeModel addNewCategoryProductType(CategoryProductTypeModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public CategoryProductTypeModel updateCategoryProductType(Integer blogId,CategoryProductTypeModel object) {
		// TODO Auto-generated method stub
		CategoryProductTypeModel blog = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		blog.setTypeName(object.getTypeName());
		return dao.save(blog);
	}
	
	@Override
	public CategoryProductTypeModel deleteCategoryProductType(Integer blogId) {
		// TODO Auto-generated method stub
		CategoryProductTypeModel object = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
