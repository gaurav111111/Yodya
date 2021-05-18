package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.CategoryProductBrandModel;

@Repository
public interface CategoryProductBrandRepository extends JpaRepository<CategoryProductBrandModel, Integer>{
	
	List<CategoryProductBrandModel> findByStatus(Boolean Status);

	Optional<CategoryProductBrandModel> findByIdAndStatus(Integer blogId, boolean b);
	CategoryProductBrandModel findById(int id);
}
