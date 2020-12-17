package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.CategoryMediaModel;

@Repository
public interface CategoryMediaRepository extends JpaRepository<CategoryMediaModel, Integer>{
	
	List<CategoryMediaModel> findByStatus(Boolean Status);

	Optional<CategoryMediaModel> findByIdAndStatus(Integer blogId, boolean b);
}
