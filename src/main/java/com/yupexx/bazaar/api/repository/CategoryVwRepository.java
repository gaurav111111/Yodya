package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.view.CategoryVwModel;
import com.yupexx.bazaar.api.model.CategoryModel;

@Repository
public interface CategoryVwRepository extends JpaRepository<CategoryVwModel, Integer>{

}
