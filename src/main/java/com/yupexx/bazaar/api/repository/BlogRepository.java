package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.BlogModel;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, Integer> {

	List<BlogModel> findByStatus(Boolean Status);
	
	Optional<BlogModel> findByIdAndStatus(Integer blogId,Boolean Status);
}