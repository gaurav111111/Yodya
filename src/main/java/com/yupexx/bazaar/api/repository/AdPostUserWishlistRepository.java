package com.yupexx.bazaar.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostUserWishlistModel;

@Repository
public interface AdPostUserWishlistRepository extends JpaRepository<AdPostUserWishlistModel, Integer> {

	AdPostUserWishlistModel findFirstByUserMasterIdAndAdId(Long userMasterId,Integer adId);

	List<AdPostUserWishlistModel> findByUserMasterId(Long userMasterId);
}