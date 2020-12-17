package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.CategoryTagsModel;

@Repository
public interface CategoryTagsRepository extends JpaRepository<CategoryTagsModel, Integer>{
	
	List<CategoryTagsModel> findByStatus(Boolean Status);

	Optional<CategoryTagsModel> findByIdAndStatus(Integer blogId, boolean b);
}
