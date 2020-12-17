package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryProductTypeModel;

public interface CategoryProductTypeInterface {

	List<CategoryProductTypeModel> getAllCategoryProductTypes();
	Optional getCategoryProductTypeById(Integer blogId);
	CategoryProductTypeModel addNewCategoryProductType(CategoryProductTypeModel object);
	CategoryProductTypeModel updateCategoryProductType(Integer blogId,CategoryProductTypeModel object);
	CategoryProductTypeModel deleteCategoryProductType(Integer blogId);
}
