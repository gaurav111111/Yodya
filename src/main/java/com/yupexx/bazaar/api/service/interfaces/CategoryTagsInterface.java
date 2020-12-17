package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryTagsModel;

public interface CategoryTagsInterface {

	List<CategoryTagsModel> getAllCategoryTagss();
	Optional getCategoryTagsById(Integer blogId);
	CategoryTagsModel addNewCategoryTags(CategoryTagsModel object);
	CategoryTagsModel updateCategoryTags(Integer blogId,CategoryTagsModel object);
	CategoryTagsModel deleteCategoryTags(Integer blogId);
}
