package com.yupexx.services.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.view.BusinessCategoryVwParentModel;

@Repository
public interface BusinessCategoryVwParentRepository extends JpaRepository<BusinessCategoryVwParentModel, Integer> {

}
