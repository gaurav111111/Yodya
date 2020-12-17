package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryProductBrandModel;

public interface CategoryProductBrandInterface {

	List<CategoryProductBrandModel> getAllCategoryProductBrands();
	Optional getCategoryProductBrandById(Integer blogId);
	CategoryProductBrandModel addNewCategoryProductBrand(CategoryProductBrandModel object);
	CategoryProductBrandModel updateCategoryProductBrand(Integer blogId,CategoryProductBrandModel object);
	CategoryProductBrandModel deleteCategoryProductBrand(Integer blogId);
}
