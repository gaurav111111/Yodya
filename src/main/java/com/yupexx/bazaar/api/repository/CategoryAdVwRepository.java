package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.view.CategoryAdVwModel;

@Repository
public interface CategoryAdVwRepository extends JpaRepository<CategoryAdVwModel, Integer>{

}
