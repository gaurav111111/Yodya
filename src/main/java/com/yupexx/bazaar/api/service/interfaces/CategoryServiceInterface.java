package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.bazaar.api.model.view.CategoryAdVwModel;
import com.yupexx.bazaar.api.model.view.CategoryVwModel;


public interface CategoryServiceInterface {
	
	public List<CategoryVwModel> getCat();
	public List<CategoryModel> getAllCat();
	public Optional getCatById(Integer catId);
	public CategoryModel addCat(CategoryModel catId);
	public CategoryModel updateCat(CategoryModel catId);
	public void deleteCat(Integer userId);
	Optional<CategoryVwModel> getCatNestedById(Integer catId);
	List<CategoryAdVwModel> getAdCat();

}
