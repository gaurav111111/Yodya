package com.yupexx.services.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessReviewJoinModel;
import com.yupexx.services.api.model.business.BusinessReviewModel;

@Repository
public interface BusinessReviewRepository extends JpaRepository<BusinessReviewModel, Integer> {

	BusinessReviewJoinModel findByIdAndStatus(Integer id,boolean status);
	
}