package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.BlogModel;
import com.yupexx.bazaar.api.model.CategoryAccessoryModel;

@Repository
public interface CategoryAccessoryRepository extends JpaRepository<CategoryAccessoryModel, Integer>{
	
	List<CategoryAccessoryModel> findByStatus(Boolean Status);

	Optional<CategoryAccessoryModel> findByIdAndStatus(Integer blogId, boolean b);
	
	CategoryAccessoryModel	findById(int id);
}
