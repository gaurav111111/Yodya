package com.yupexx.bazaar.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.view.CategoryVwModel;
import com.yupexx.bazaar.api.model.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer>{

	List<CategoryModel> findBycategoryName(String catName);
	
}
