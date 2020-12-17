package com.yupexx.services.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.view.BusinessCategoryVwModel;


@Repository
public interface BusinessCategoryVwRepository extends JpaRepository<BusinessCategoryVwModel, Integer> {

}