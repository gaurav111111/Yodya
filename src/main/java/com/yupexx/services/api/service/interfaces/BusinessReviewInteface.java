package com.yupexx.services.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.services.api.model.business.BusinessReviewJoinModel;
import com.yupexx.services.api.model.business.BusinessReviewLikesModel;
import com.yupexx.services.api.model.business.BusinessReviewMediaModel;
import com.yupexx.services.api.model.business.BusinessReviewModel;
import com.yupexx.services.api.model.business.BusinessUserWishListModel;

public interface BusinessReviewInteface {

	List<BusinessReviewJoinModel> getAllReview();
	
	List<BusinessReviewJoinModel> getRecentReview();
	
	List<BusinessReviewJoinModel> getUserReview(Long userId);
	
	Optional<BusinessReviewJoinModel> getReviewById(Integer businessId);
	
	BusinessReviewModel createReview(BusinessReviewModel object);
	
	BusinessReviewLikesModel createLike(BusinessReviewLikesModel object);
	
	BusinessReviewJoinModel updateReivew(Integer id);
	
	Boolean deleteReview(Integer id);
	
	void ReviewMedia(BusinessReviewMediaModel media);
	
	List<BusinessReviewLikesModel> getUserLikes(Long userId);
	
}
