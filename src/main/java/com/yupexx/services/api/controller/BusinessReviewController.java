package com.yupexx.services.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.business.BusinessJoinModel;
import com.yupexx.services.api.model.business.BusinessReviewJoinModel;
import com.yupexx.services.api.model.business.BusinessReviewLikesModel;
import com.yupexx.services.api.model.business.BusinessReviewMediaModel;
import com.yupexx.services.api.model.business.BusinessReviewModel;
import com.yupexx.services.api.model.dto.BussinessReviewJoinModelDTO;
import com.yupexx.services.api.repository.BusinessJoinRepository;
import com.yupexx.services.api.request.BusinessReviewRequest;
import com.yupexx.services.api.service.BusinessReviewService;

@RestController
@CrossOrigin
public class BusinessReviewController {

	@Autowired
	BusinessReviewService service;

	@Autowired
	UserIdentity identity;
	
	@Autowired
	BusinessJoinRepository busJoin;
	
	
	@RequestMapping(value = "/services/business/review", method = RequestMethod.GET)
	public 	List<BusinessReviewJoinModel> getAllReview(){
		System.out.println(this.getClass().getSimpleName() + " - Get all Business Review service is invoked.");
		
	 return service.getAllReview();
	
	}
	
	@RequestMapping(value = "/services/business/recentReview", method = RequestMethod.GET)
	public List<BussinessReviewJoinModelDTO> getRecentReview(){
		System.out.println(this.getClass().getSimpleName() + " - Get all Business Review service is invoked.");
	   Message msg = new Message();
		List<BusinessReviewJoinModel> data= service.getRecentReview();
		List<BusinessJoinModel> bjData = busJoin.findByStatus(true);
		
		List<BussinessReviewJoinModelDTO> finalData = new ArrayList<BussinessReviewJoinModelDTO>();
		int check=0;
		for(BusinessReviewJoinModel br:data) {
			BussinessReviewJoinModelDTO temp = new BussinessReviewJoinModelDTO();
			check=0;
			System.out.println("br.getBusinessId()"+br.getBusinessId());
			for(BusinessJoinModel bj:bjData) {
				if(br.getBusinessId()==bj.getId()) {
					System.out.println("log");
					temp.setBusinessName(bj.getBusinessName());
					temp.setBusinessReviewJoinModel(br);
					finalData.add(temp);
					check=1;
				}
			}
			if(check==0) {
				temp.setBusinessName("");
				temp.setBusinessReviewJoinModel(br);
				finalData.add(temp);
			}
		}
		msg.setCode(200);
		msg.setContentList(finalData);
		
		return finalData;
	}
	
	@RequestMapping(value = "/services/business/userReview/{userId}", method = RequestMethod.GET)
	public List<BusinessReviewJoinModel> getUserReview(@PathVariable Long userId){
		System.out.println(this.getClass().getSimpleName() + " - Get all User Review service is invoked.");
		return service.getUserReview(userId);
	}
	
	@RequestMapping(value = "/services/business/userSelfReview", method = RequestMethod.GET)
	public List<BusinessReviewJoinModel> getUserSelfReview(Authentication authentication){
		System.out.println(this.getClass().getSimpleName() + " - Get all User Review service is invoked.");
		UserModel user = identity.get(authentication);
		return service.getUserReview(user.getId());
	}
	
	@RequestMapping(value = "/services/business/review/{id}", method = RequestMethod.GET)
	public Optional<BusinessReviewJoinModel> getReviewById(@PathVariable Integer id) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Business Review service is invoked.");
		return service.getReviewById(id);
	}
		
	@RequestMapping(value = "/services/business/like/{reviewId}", method = RequestMethod.PATCH)
	public BusinessReviewLikesModel ReviewLike(@Valid @PathVariable Integer reviewId, Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Create new Review Post method is invoked.");
		UserModel user = identity.get(authentication);
		BusinessReviewLikesModel reviewLike = new BusinessReviewLikesModel();
		reviewLike.setReviewId(reviewId);
		reviewLike.setUserMasterId(user.getId());
		reviewLike.setCreatedBy(user.getId());
		reviewLike.setUpdatedBy(user.getId());
		
		return service.createLike(reviewLike);
	}
	
	@RequestMapping(value = "/services/business/userlikes", method = RequestMethod.GET)
	public List<BusinessReviewLikesModel> userLike(Authentication authentication) throws Exception {
	System.out.println(this.getClass().getSimpleName() + " - GET Linked ad to wishlist for user ");
		
		UserModel user = identity.get(authentication);
	
		identity.checkIfResourceAllowedForRole(user.getId(), authentication);
	
		return service.getUserLikes(user.getId());
	}
	
	
	
	@RequestMapping(value = "/services/business/review", method = RequestMethod.POST)
	public BusinessReviewModel createReview(@Valid @RequestBody BusinessReviewRequest request, Authentication authentication) throws Exception {
			
		System.out.println(this.getClass().getSimpleName() + " - Create new Review Post method is invoked.");
		
		UserModel user = identity.get(authentication);
		
		BusinessReviewModel review = new BusinessReviewModel();
		review.setBusinessId(request.getBusinessId());
		review.setDescription(request.getDescription());
		review.setServiceQuality(request.getServiceQuality());
		review.setStaffBehaviour(request.getStaffBehaviour());
		review.setAmbience(request.getAmbience());
		review.setValueMoney(request.getValueMoney());
		review.setOverall(request.getOverall());
		
		review.setCreatedBy(user.getId());
		review.setUpdatedBy(user.getId());
		
		BusinessReviewModel createdReview = service.createReview(review);
		
		request.getMedia().forEach(murl->{
			
			BusinessReviewMediaModel media = new BusinessReviewMediaModel();
			media.setReviewId(createdReview.getId());
			media.setUrl(murl);
			service.ReviewMedia(media);
			
		});
		
		return createdReview;
	}
	
	@RequestMapping(value = "/services/business/review/{reviewId}", method = RequestMethod.DELETE)
	public void deleteReview(@PathVariable Integer reviewId, Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Delete Review method is invoked.");
		
		UserModel user = identity.get(authentication);
		
		identity.checkIfResourceAllowedForRole(user.getId(), authentication);
		
		service.deleteReview(reviewId);
		
	}
	
}
