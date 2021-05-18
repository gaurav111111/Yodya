package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryMediaModel;
import com.yupexx.bazaar.api.repository.CategoryMediaRepository;
import com.yupexx.bazaar.api.service.interfaces.CategoryMediaInterface;

@Service
public class CategoryMediaService implements CategoryMediaInterface {

	@Autowired
	CategoryMediaRepository dao;
	
	@Override
	public List<CategoryMediaModel> getAllCategoryMedias() {
		// TODO Auto-generated method stub
	//	return dao.findByStatus(true);
		return dao.findAll();
	}

	@Override
	public Optional<CategoryMediaModel> getCategoryMediaById(Integer objectclassId) {
		// TODO Auto-generated method stub
		//return dao.findByIdAndStatus(objectclassId,true);
		return dao.findById(objectclassId);
	}

	@Override
	public CategoryMediaModel addNewCategoryMedia(CategoryMediaModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public CategoryMediaModel updateCategoryMedia(Integer objectclassId,CategoryMediaModel object) {
		// TODO Auto-generated method stub
		CategoryMediaModel objectclass = dao.findById(objectclassId).orElseThrow(() -> new EntityNotFoundException());
		objectclass.setMaxImage(object.getMaxImage());
		objectclass.setMaxVideo(object.getMaxVideo());
		objectclass.setPackDescription(object.getPackDescription());
		objectclass.setPackName(object.getPackName());
		objectclass.setPackPrice(object.getPackPrice());
		return dao.save(objectclass);
	}
	
	@Override
	public CategoryMediaModel deleteCategoryMedia(Integer objectclassId) {
		// TODO Auto-generated method stub
		CategoryMediaModel object = dao.findById(objectclassId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
