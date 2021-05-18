package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CategoryDetailsModel;

public interface CategoryDetailsInterface {
	
	 List<CategoryDetailsModel> getAllCatDetails();
	Optional<CategoryDetailsModel> getCatDetailsById(int id);
	
	

}
