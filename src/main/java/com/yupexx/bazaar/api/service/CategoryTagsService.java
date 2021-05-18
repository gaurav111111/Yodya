package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryTagsModel;
import com.yupexx.bazaar.api.repository.CategoryTagsRepository;
import com.yupexx.bazaar.api.service.interfaces.CategoryTagsInterface;

@Service
public class CategoryTagsService implements CategoryTagsInterface {

	@Autowired
	CategoryTagsRepository dao;
	
	@Override
	public List<CategoryTagsModel> getAllCategoryTagss() {
		// TODO Auto-generated method stub
		//return dao.findByStatus(true);
		return dao.findAll();
	}

	@Override
	public Optional<CategoryTagsModel> getCategoryTagsById(Integer blogId) {
		// TODO Auto-generated method stub
	//	return dao.findByIdAndStatus(blogId,true);
		return dao.findById(blogId);
	}

	@Override
	public CategoryTagsModel addNewCategoryTags(CategoryTagsModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public CategoryTagsModel updateCategoryTags(Integer blogId,CategoryTagsModel object) {
		// TODO Auto-generated method stub
		CategoryTagsModel blog = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		blog.setTagName(object.getTagName());
		return dao.save(blog);
	}
	
	@Override
	public CategoryTagsModel deleteCategoryTags(Integer blogId) {
		// TODO Auto-generated method stub
		CategoryTagsModel object = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
