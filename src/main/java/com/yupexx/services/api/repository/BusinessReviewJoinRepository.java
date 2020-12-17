package com.yupexx.services.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessReviewJoinModel;

@Repository
public interface BusinessReviewJoinRepository extends JpaRepository<BusinessReviewJoinModel, Integer> {
	
	Optional<BusinessReviewJoinModel> findByIdAndStatus(Integer businessId, boolean b);
		
	List<BusinessReviewJoinModel> findByCreatedBy(Long userId);
	
	List<BusinessReviewJoinModel> findTop2ByStatusOrderByUpdatedDateDesc(boolean status);
	
}