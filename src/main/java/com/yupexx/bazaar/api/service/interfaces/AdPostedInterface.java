package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

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

public interface AdPostedInterface {
	
	public List<AdPostUserJoinModel> getAdPosted();

	AdPostUserModel postAd(AdPostUserModel postAd);

	AdPostCategoryModel postCat(AdPostCategoryModel catAd);

	AdPostTagModel postTag(AdPostTagModel object);

	AdPostAccessoryModel postAcc(AdPostAccessoryModel object);

	AdPostBrandModel postBrand(AdPostBrandModel object);

	AdPostUserSubscriptionsModel subscription(AdPostUserSubscriptionsModel object);

	AdPostUserWishlistModel wishlist(AdPostUserWishlistModel object);

	List<AdPostUserJoinModel> getSimilarAds(Integer adId,Integer catId);

	List<AdPostUserJoinModel> getNearByAds(String location);

	List<AdPostUserJoinModel> getAdSearch(String key);	

	List<AdPostUserJoinModel> getAdPostedBySeller(Long sellerId);

	AdPostUserModel deleteAdPosted(Integer adId);

	Optional<AdPostUserJoinModel> getAdPostedById(Integer adId);

	List<AdPostUserWishlistModel> getWishlist(Long userMasterId);
	
	List<AdPostDealModel> getAllDeals();

	AdPostMediaModel postMedia(AdPostMediaModel object);

	Optional<AdPostDealModel> getDealById(Integer dealId);

	List<AdPostUserJoinModel> getAdByCatId(Integer catId);

	List<AdPostUserJoinModel> getFeaturedAdPosted();

	List<AdPostUserJoinModel> getAdPostedByFilter(List<Integer> catId);

}
