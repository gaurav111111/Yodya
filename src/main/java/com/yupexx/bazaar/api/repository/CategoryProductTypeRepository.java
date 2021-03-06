package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.CategoryProductTypeModel;


@Repository
public interface CategoryProductTypeRepository extends JpaRepository<CategoryProductTypeModel, Integer>{
	
	List<CategoryProductTypeModel> findByStatus(Boolean Status);

	Optional<CategoryProductTypeModel> findByIdAndStatus(Integer blogId, boolean b);
	
	CategoryProductTypeModel  findById(int id);
}
