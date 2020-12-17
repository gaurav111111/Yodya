package com.yupexx.services.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessUserWishListModel;

@Repository
public interface BusinessUserWishListRepository extends JpaRepository<BusinessUserWishListModel, Integer> {

	List<BusinessUserWishListModel> findByStatus(boolean b);
	
	List<BusinessUserWishListModel> findByCreatedBy(Long userId);

	//BusinessUserWishListModel findByIdAndStatus(Integer id,boolean b);

	BusinessUserWishListModel findFirstByUserMasterIdAndBusinessId(Long userMasterId, Integer businessId);

}