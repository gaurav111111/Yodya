package com.yupexx.services.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessReviewLikesModel;

@Repository
public interface BusinessReviewLikesRepository extends JpaRepository<BusinessReviewLikesModel, Integer> {
	
	List<BusinessReviewLikesModel> findByCreatedBy(Long userId);
	
	BusinessReviewLikesModel findFirstByUserMasterIdAndReviewId(Long userMasterId, Integer reviewId);

}