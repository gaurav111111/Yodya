package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.CategoryDetailsModel;


@Repository
public interface CategoryDetailRepository  extends JpaRepository<CategoryDetailsModel, Integer> {

	

	

}
