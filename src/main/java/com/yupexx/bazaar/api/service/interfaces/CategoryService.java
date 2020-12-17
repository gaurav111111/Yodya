package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.bazaar.api.model.view.CategoryAdVwModel;
import com.yupexx.bazaar.api.model.view.CategoryVwModel;
import com.yupexx.bazaar.api.repository.CategoryAdVwRepository;
import com.yupexx.bazaar.api.repository.CategoryRepository;
import com.yupexx.bazaar.api.repository.CategoryVwRepository;

@Service
public class CategoryService implements CategoryServiceInterface {
	@Autowired
    CategoryRepository dao;
	@Autowired
	CategoryVwRepository daoVw;
	@Autowired
	CategoryAdVwRepository daoAdVw;

	@Override
	public List<CategoryAdVwModel> getAdCat() {
		// TODO Auto-generated method stub
		return daoAdVw.findAll();
	}
	
	@Override
	public List<CategoryVwModel> getCat() {
		// TODO Auto-generated method stub
		return daoVw.findAll();
	}
	
	@Override
	public List<CategoryModel> getAllCat() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Optional<CategoryModel> getCatById(Integer catId) {
		// TODO Auto-generated method stub
		return dao.findById(catId);
	}
	
	@Override
	public Optional<CategoryVwModel> getCatNestedById(Integer catId) {
		// TODO Auto-generated method stub
		return daoVw.findById(catId);
	}

	@Override
	public CategoryModel addCat(CategoryModel cat) {
		// TODO Auto-generated method stub
		return (CategoryModel) dao.save(cat);
	}

	@Override
	public CategoryModel updateCat(CategoryModel cat) {
		// TODO Auto-generated method stub
		return (CategoryModel) dao.save(cat);
	}

	@Override
	public void deleteCat(Integer catId) {
		// TODO Auto-generated method stub
		dao.deleteById(catId);
	}

}
