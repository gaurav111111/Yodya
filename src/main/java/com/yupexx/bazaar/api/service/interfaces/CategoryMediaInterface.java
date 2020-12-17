package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryMediaModel;

public interface CategoryMediaInterface {

	List<CategoryMediaModel> getAllCategoryMedias();
	Optional getCategoryMediaById(Integer blogId);
	CategoryMediaModel addNewCategoryMedia(CategoryMediaModel object);
	CategoryMediaModel updateCategoryMedia(Integer blogId,CategoryMediaModel object);
	CategoryMediaModel deleteCategoryMedia(Integer blogId);
}
