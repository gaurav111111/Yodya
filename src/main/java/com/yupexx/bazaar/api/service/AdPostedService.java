package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.ChatModel;
import com.yupexx.bazaar.api.model.ads.AdPostAccessoryModel;
import com.yupexx.bazaar.api.model.ads.AdPostBrandModel;
import com.yupexx.bazaar.api.model.ads.AdPostCategoryModel;
import com.yupexx.bazaar.api.model.ads.AdPostDealModel;
import com.yupexx.bazaar.api.model.ads.AdPostMediaModel;
import com.yupexx.bazaar.api.model.ads.AdPostTagModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserJoinModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserSubscriptionsModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserWishlistModel;
import com.yupexx.bazaar.api.repository.AdPostAccessoryRepository;
import com.yupexx.bazaar.api.repository.AdPostBrandRepository;
import com.yupexx.bazaar.api.repository.AdPostDealRepository;
import com.yupexx.bazaar.api.repository.AdPostMediaRepository;
import com.yupexx.bazaar.api.repository.AdPostTagRepository;
import com.yupexx.bazaar.api.repository.AdPostUserCatRepository;
import com.yupexx.bazaar.api.repository.AdPostUserSubscriptionRepository;
import com.yupexx.bazaar.api.repository.AdPostUserWishlistRepository;
import com.yupexx.bazaar.api.repository.AdPostedRepository;
import com.yupexx.bazaar.api.repository.AdPostedVwRepository;
import com.yupexx.bazaar.api.service.interfaces.AdPostedInterface;

@Service
public class AdPostedService implements AdPostedInterface {
	@Autowired
	AdPostedVwRepository daoVw;
	
	@Autowired
	AdPostedRepository dao;
	
	@Autowired
	AdPostUserCatRepository daoCat;
	
	@Autowired
	AdPostBrandRepository daoBrand;
	
	@Autowired
	AdPostAccessoryRepository daoAcc;
	
	@Autowired
	AdPostTagRepository daoTag;
	
	@Autowired
	AdPostUserSubscriptionRepository  daoSub;
	
	@Autowired
	AdPostUserWishlistRepository daoWishlist;
	
	@Autowired
	AdPostDealRepository daoDeal;
	
	@Autowired
	AdPostMediaRepository daoMedia;
	
	@Override
	public List<AdPostUserJoinModel> getAdPosted() {
		// TODO Auto-generated method stub
		return daoVw.findTop12ByStatusOrderByUpdatedDateDesc(true);
	}
	
	@Override
	public List<AdPostUserJoinModel> getFeaturedAdPosted() {
		// TODO Auto-generated method stub
		return daoVw.findTop12ByAdPriceGreaterThanAndStatusOrderByUpdatedDateDesc(0,true);
	}
	
	@Override
	public AdPostUserModel deleteAdPosted(Integer adId) {
		// TODO Auto-generated method stub
		AdPostUserModel object = dao.findOneByIdAndStatus(adId, true);
		if(object==null) {
			throw new EntityNotFoundException("Ad Post not found");
		}
		object.setStatus(false);
		return dao.save(object);
	}
	
	@Override
	public Optional<AdPostUserJoinModel> getAdPostedById(Integer adId) {
		// TODO Auto-generated method stub
		return daoVw.findById(adId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdPostedBySeller(Long sellerId) {
		// TODO Auto-generated method stub
		return daoVw.findByCreatedByAndStatusOrderByUpdatedDateDesc(sellerId,true);
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdPostedByFilter(List<Integer> catId) {
		// TODO Auto-generated method stub
		return daoVw.findByCatIds(catId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getSimilarAds(Integer adId,Integer catId) {
		// TODO Auto-generated method stub
		return daoVw.findByStatusAndCatIdAndIdNotOrderByUpdatedDateDesc(true,catId,adId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdByCatId(Integer catId) {
		// TODO Auto-generated method stub
		return daoVw.findByStatusAndCatIdOrderByUpdatedDateDesc(true,catId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getNearByAds(String location) {
		// TODO Auto-generated method stub
		return daoVw.findByStatusAndLocationContainingOrderByUpdatedDateDesc(true,location);
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdSearch(String key) {
		// TODO Auto-generated method stub
		return daoVw.findByStatusAndAdTitleContainingOrAdDescriptionContainingOrderByUpdatedDateDesc(true,key,key);
	}
	
	@Override
	public AdPostUserModel postAd(AdPostUserModel postAd) {
		// TODO Auto-generated method stub
		return dao.save(postAd);
	}
	
	@Override
	public AdPostCategoryModel postCat(AdPostCategoryModel catAd) {
		// TODO Auto-generated method stub
		return daoCat.save(catAd);
	}
	
	@Override
	public AdPostBrandModel postBrand(AdPostBrandModel object) {
		// TODO Auto-generated method stub
		return daoBrand.save(object);
	}
	
	@Override
	public AdPostAccessoryModel postAcc(AdPostAccessoryModel object) {
		// TODO Auto-generated method stub
		return daoAcc.save(object);
	}
	
	@Override
	public AdPostTagModel postTag(AdPostTagModel object) {
		// TODO Auto-generated method stub
		return daoTag.save(object);
	}
	
	@Override
	public AdPostMediaModel postMedia(AdPostMediaModel object) {
		// TODO Auto-generated method stub
		return daoMedia.save(object);
	}
	
	@Override
	public AdPostUserSubscriptionsModel subscription(AdPostUserSubscriptionsModel object) {
		// TODO Auto-generated method stub
		return daoSub.save(object);
	}
	
	@Override
	public List<AdPostUserWishlistModel> getWishlist(Long userMasterId) {
		// TODO Auto-generated method stub
		return daoWishlist.findByUserMasterId(userMasterId);
	}
	
	@Override
	public AdPostUserWishlistModel wishlist(AdPostUserWishlistModel object) {
		// TODO Auto-generated method stub
		AdPostUserWishlistModel objectExist = 
				daoWishlist.findFirstByUserMasterIdAndAdId(object.getUserMasterId(),object.getAdId());
		if(objectExist!=null) {
			daoWishlist.delete(objectExist);
			return object;
		}
		return daoWishlist.save(object);
	}

	@Override
	public List<AdPostDealModel> getAllDeals() {
		// TODO Auto-generated method stub
		return daoDeal.findByStatus(true);
	}
	
	@Override
	public Optional<AdPostDealModel> getDealById(Integer dealId) {
		// TODO Auto-generated method stub
		return daoDeal.findByIdAndStatus(dealId,true);
	}
	
	
}
