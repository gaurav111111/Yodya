package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryAccessoryModel;

public interface CategoryAccessoryInterface {

	List<CategoryAccessoryModel> getAllCategoryAccessorys();
	Optional getCategoryAccessoryById(Integer blogId);
	CategoryAccessoryModel addNewCategoryAccessory(CategoryAccessoryModel object);
	CategoryAccessoryModel updateCategoryAccessory(Integer blogId,CategoryAccessoryModel object);
	CategoryAccessoryModel deleteCategoryAccessory(Integer blogId);
}
