package com.yupexx.bazaar.api.controller;

import java.sql.Timestamp;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.ads.AdPostAccessoryModel;
import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.ads.AdPostBrandModel;
import com.yupexx.bazaar.api.model.ads.AdPostCategoryModel;
import com.yupexx.bazaar.api.model.ads.AdPostDealModel;
import com.yupexx.bazaar.api.model.ads.AdPostMediaModel;
import com.yupexx.bazaar.api.model.ads.AdPostTagModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserJoinModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserSubscriptionsModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserWishlistModel;
import com.yupexx.bazaar.api.model.dto.AdPostFilterDTO;
import com.yupexx.bazaar.api.model.dto.AdPostUserDTO;
import com.yupexx.bazaar.api.model.view.CategoryAdVwModel;
import com.yupexx.bazaar.api.service.AdPostedService;
import com.yupexx.bazaar.api.service.interfaces.CategoryService;

@RestController
public class AdsPostedController {

	@Autowired
	AdPostedService service;
	
	@Autowired
	CategoryService servicecat;
	
	@Autowired
	UserIdentity identity;

	@RequestMapping(value = "/bazaar/ads/posted", method = RequestMethod.GET)
	public List<AdPostUserJoinModel> getAdPosted() {
		System.out.println(this.getClass().getSimpleName() + " - Get all AdPosted service is invoked.");
		return service.getAdPosted();
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/featured", method = RequestMethod.GET)
	public List<AdPostUserJoinModel> getFeaturedAdPosted() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Featured AdPosted service is invoked.");
		return service.getFeaturedAdPosted();
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/filter", method = RequestMethod.POST)
	public List<AdPostUserJoinModel> getAdPostedFilter(@Valid @RequestBody AdPostFilterDTO DTO) {
		System.out.println(this.getClass().getSimpleName() + " - Get all AdPosted Filter service is invoked.");
		return service.getAdPostedByFilter(DTO.getCatId());
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/{adId}", method = RequestMethod.GET)
	public Optional<AdPostUserJoinModel> getAdPostedById(@PathVariable Integer adId) {
		System.out.println(this.getClass().getSimpleName() + " - GET AdPosted by Ad ID service is invoked.");
		return service.getAdPostedById(adId);
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/seller/{sellerId}", method = RequestMethod.GET)
	public List<AdPostUserJoinModel> getAdPostedBySeller(@Valid @PathVariable Long sellerId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all AdPosted service is invoked.");
		return service.getAdPostedBySeller(sellerId);
	}
	
	
	@RequestMapping(value = "/bazaar/ads/posted", method = RequestMethod.POST)
	public AdPostUserModel createAd(@Valid @RequestBody AdPostUserDTO postAd,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Create new Post method is invoked.");
		
		UserModel user = identity.get(authentication);
		
		Float adSubscriptionPrice = (float)0.00;
		Timestamp currentDate = new Timestamp(new Date().getTime());
		Timestamp expiryDate = new Timestamp(new Date().getTime());
		Date currentD = new Date();
		Date expiryD = new Date();
		
		Optional<AdPostDealModel> deal = service.getDealById(postAd.getAdSubscriptionId());
		if(deal.isPresent()==false) {
			throw new Exception("Deal not found");
		}
		
		expiryD = identity.addDays(currentD, deal.get().getDealPeriod());
		expiryDate = new Timestamp(expiryD.getTime());
		
		//identity.differenceDays(currentD, expiryD);
		
		AdPostUserModel creatAd = new AdPostUserModel();
		creatAd.setAdTitle(postAd.getAdTitle());
		creatAd.setAdDescription(postAd.getAdDescription());
		creatAd.setAdPrice(postAd.getAdPrice());
		creatAd.setLocation(postAd.getLocation());
		creatAd.setProductCondition(postAd.getProductCondition());
		creatAd.setProductModelName(postAd.getProductModelName());
		creatAd.setAdCurrency(postAd.getAdCurrency());
		creatAd.setManufacturedYear(postAd.getManufacturedYear());
		
		creatAd.setCatId(postAd.getCatId());
		creatAd.setCreatedBy(user.getId());
		creatAd.setUpdatedBy(user.getId());
		
		
		AdPostUserModel created =  service.postAd(creatAd);
		
		postAd.getCatProductTypeId().forEach(pId -> {
			AdPostCategoryModel cat = new AdPostCategoryModel();
			cat.setAdId(created.getId());
			cat.setCatProductTypeId(pId);
			service.postCat(cat);
		});
		
		postAd.getBrandId().forEach(bId -> {
			AdPostBrandModel brand = new AdPostBrandModel();
			brand.setAdId(created.getId());
			brand.setBrandId(bId);
			service.postBrand(brand);
		});
		
		postAd.getAccessoryId().forEach(aId -> {
			AdPostAccessoryModel acc = new AdPostAccessoryModel();
			acc.setAdId(created.getId());
			acc.setAccessoryId(aId);
			service.postAcc(acc);
		});
		
		postAd.getTagId().forEach(tId -> {
			AdPostTagModel tag = new AdPostTagModel();
			tag.setAdId(created.getId());
			tag.setTagId(tId);
			service.postTag(tag);
		});
		
		postAd.getCatMediaId().forEach(cmId -> {
			AdPostMediaModel media = new AdPostMediaModel();
			media.setAdId(created.getId());
			media.setUrl(cmId);
			service.postMedia(media);
		});
		
		AdPostUserSubscriptionsModel subscription = new AdPostUserSubscriptionsModel();
		subscription.setAdId(creatAd.getId());
		subscription.setUserMasterId(user.getId());
		subscription.setCreatedBy(user.getId());
		subscription.setUpdatedBy(user.getId());
		subscription.setAdSubscriptionId(postAd.getAdSubscriptionId());
		subscription.setAdSubscriptionPrice(adSubscriptionPrice);
		subscription.setExpiryDate(expiryDate);
		subscription.setStatus(true);
		subscription.setPaymentPaid(false);
		
		service.subscription(subscription);
		
		return created;
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/{adId}", method = RequestMethod.DELETE)
	public AdPostUserModel deleteAdPosted(@PathVariable Integer adId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete AdPosted service is invoked.");
		return service.deleteAdPosted(adId);
	}
	
	@RequestMapping(value = "/bazaar/ads/wishlist/{userMasterId}", method = RequestMethod.GET)
	public List<AdPostUserWishlistModel> getAdWishList(@PathVariable Long userMasterId,Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - GET Linked ad to wishlist for user ");
		
		identity.checkIfResourceAllowedForRole(userMasterId, authentication);
		
		return service.getWishlist(userMasterId);
	
	}

	
	@RequestMapping(value = "/bazaar/ads/wishlist/{adId}", method = RequestMethod.PATCH)
	public AdPostUserWishlistModel adWishList(@PathVariable Integer adId,Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Link ad to wishlist for user ");
		
		UserModel user = identity.get(authentication);

		AdPostUserWishlistModel wishlist = new AdPostUserWishlistModel();
		wishlist.setAdId(adId);
		wishlist.setUserMasterId(user.getId());
		wishlist.setCreatedBy(user.getId());
		wishlist.setUpdatedBy(user.getId());
		wishlist.setStatus(true);
		return service.wishlist(wishlist);
	
	}
	
	@RequestMapping(value = "/bazaar/ads/similar/{adId}/catId/{catId}", method = RequestMethod.GET)
	public List<AdPostUserJoinModel> getSimilarAds(@PathVariable Integer adId,@PathVariable Integer catId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Similar AdPosted service is invoked.");
		return service.getSimilarAds(adId,catId);
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/catId/{catId}", method = RequestMethod.GET)
	public List<CategoryAdVwModel> getAdByCatId(@PathVariable Integer catId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Similar AdPosted service is invoked.");
		CategoryModel cm = this.getFirstLevelCat(catId);
		return servicecat.getAdCat();
	}
	
	@RequestMapping(value = "/bazaar/ads/latest/nearBy", method = RequestMethod.GET)
	public List<AdPostUserJoinModel> getNearByAds(@RequestParam String location) {
		System.out.println(this.getClass().getSimpleName() + " - Get all NearBy AdPosted service is invoked.");
		return service.getNearByAds(location);
	}
	
	@RequestMapping(value = "/bazaar/ads/search", method = RequestMethod.GET)
	public List<AdPostUserJoinModel> getAdSearch(@RequestParam String key) {
		System.out.println(this.getClass().getSimpleName() + " - Get all NearBy AdPosted service is invoked.");
		return service.getAdSearch(key);
	}
	
	@RequestMapping(value = "/bazaar/ads/deals", method = RequestMethod.GET)
	public List<AdPostDealModel> getAllDeals() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Deals AdPosted service is invoked.");
		return service.getAllDeals();
	}
	
	/**
	 * 
	 * @param catId
	 * @return
	 */
	public CategoryModel getFirstLevelCat(Integer catId) {
		CategoryModel cm = servicecat.getCatById(catId).get();
		if(cm.getParentCatId() !=null) {
			return this.getFirstLevelCat(cm.getParentCatId());
		}else {
			return cm;
		}
		
		//System.out.println(cm.getParentCatId());
		
	}
}
