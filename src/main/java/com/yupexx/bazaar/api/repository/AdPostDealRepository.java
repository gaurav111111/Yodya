package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostDealModel;

@Repository
public interface AdPostDealRepository extends JpaRepository<AdPostDealModel, Integer> {
	
	List<AdPostDealModel> findByStatus(Boolean status);

	Optional<AdPostDealModel> findByIdAndStatus(Integer dealId, boolean b);
}