package com.yupexx.services.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.services.api.model.BusinessCategoryModel;
import com.yupexx.services.api.model.view.BusinessCategoryVwModel;
import com.yupexx.services.api.model.view.BusinessCategoryVwParentModel;


public interface BusinessCategoryServiceInterface {
	
	public List<BusinessCategoryVwParentModel> getCat();
	public List<BusinessCategoryModel> getAllCat();
	public Optional getCatById(Integer catId);
	public BusinessCategoryModel addCat(BusinessCategoryModel catId);
	public BusinessCategoryModel updateCat(BusinessCategoryModel catId);
	public void deleteCat(Integer userId);
	Optional<BusinessCategoryVwParentModel> getCatNestedById(Integer catId);
	List<BusinessCategoryVwModel> getAdCat();

}
