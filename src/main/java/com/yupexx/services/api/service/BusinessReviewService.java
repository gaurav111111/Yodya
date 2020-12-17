package com.yupexx.services.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.services.api.model.business.BusinessReviewJoinModel;
import com.yupexx.services.api.model.business.BusinessReviewLikesModel;
import com.yupexx.services.api.model.business.BusinessReviewMediaModel;
import com.yupexx.services.api.model.business.BusinessReviewModel;
import com.yupexx.services.api.repository.BusinessReviewJoinRepository;
import com.yupexx.services.api.repository.BusinessReviewLikesRepository;
import com.yupexx.services.api.repository.BusinessReviewMediaRepository;
import com.yupexx.services.api.repository.BusinessReviewRepository;
import com.yupexx.services.api.service.interfaces.BusinessReviewInteface;

@Service
public class BusinessReviewService implements BusinessReviewInteface {
	
	@Autowired
	BusinessReviewJoinRepository daoJoin;
	
	@Autowired
	BusinessReviewRepository dao;
	
	@Autowired
	BusinessReviewMediaRepository daoMedia;
	
	@Autowired
	BusinessReviewLikesRepository daoLike;

	@Override
	public List<BusinessReviewJoinModel> getAllReview() {
		// TODO Auto-generated method stub
		return daoJoin.findAll();
	}


	@Override
	public List<BusinessReviewJoinModel> getRecentReview() {
		// TODO Auto-generated method stub
		return daoJoin.findTop2ByStatusOrderByUpdatedDateDesc(true);
	}

	@Override
	public List<BusinessReviewJoinModel> getUserReview(Long userId) {
		// TODO Auto-generated method stub
		return daoJoin.findByCreatedBy(userId);
	}
	
	@Override
	public List<BusinessReviewLikesModel> getUserLikes(Long userId) {
		// TODO Auto-generated method stub
		return daoLike.findByCreatedBy(userId);
	}
	
	@Override
	public Optional<BusinessReviewJoinModel> getReviewById(Integer businessId) {
		// TODO Auto-generated method stub
		return daoJoin.findByIdAndStatus(businessId, true);
	}
	
	@Override
	public BusinessReviewModel createReview(BusinessReviewModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public void ReviewMedia(BusinessReviewMediaModel media) {
		// TODO Auto-generated method stub
		daoMedia.save(media);
	}
	
	@Override
	public BusinessReviewLikesModel createLike(BusinessReviewLikesModel object) {
		// TODO Auto-generated method stub
		
		BusinessReviewLikesModel objectExist = daoLike.findFirstByUserMasterIdAndReviewId(object.getUserMasterId(), object.getReviewId());
				
		if(objectExist!=null) {
			daoLike.delete(objectExist);
			return object;
		}
		
		daoLike.save(object);
		
		return daoLike.findById(object.getId()).get();
	}

	@Override
	public BusinessReviewJoinModel updateReivew(Integer id) {
		// TODO Auto-generated method stub
		BusinessReviewJoinModel object = dao.findByIdAndStatus(id,true);
		return daoJoin.save(object);
	}

	@Override
	public Boolean deleteReview(Integer id) {
		// TODO Auto-generated method stub
			dao.deleteById(id);
		return true;
	}



}
