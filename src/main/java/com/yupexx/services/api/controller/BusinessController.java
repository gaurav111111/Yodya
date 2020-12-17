package com.yupexx.services.api.controller;
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

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.services.api.model.BusinessCategoryModel;
import com.yupexx.services.api.model.business.BusinessFacilityModel;
import com.yupexx.services.api.model.business.BusinessJoinModel;
import com.yupexx.services.api.model.business.BusinessMasterModel;
import com.yupexx.services.api.model.business.BusinessMediaModel;
import com.yupexx.services.api.model.business.BusinessReportModel;
import com.yupexx.services.api.model.business.BusinessUserWishListModel;

import com.yupexx.services.api.request.BusinessFilterRequest;
import com.yupexx.services.api.request.BusinessReportRequest;
import com.yupexx.services.api.request.BusinessRequest;
import com.yupexx.services.api.service.BusinessService;
import com.yupexx.services.api.service.BusinessCategoryService;

@RestController
public class BusinessController {

	@Autowired
	BusinessService service;
	
	@Autowired
	BusinessCategoryService servicecat;
	
	@Autowired
	UserIdentity identity;

	@RequestMapping(value = "/services/business/posted", method = RequestMethod.GET)
	public List<BusinessJoinModel> getBusiness() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Business service is invoked.");
		return service.getAllBusiness();
	}
	
	@RequestMapping(value = "/services/business/posted/sponsored", method = RequestMethod.GET)
	public List<BusinessJoinModel> getSponsoredBusiness() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Sponsored Business service is invoked.");
		return service.getSponsoredBusiness();
	}
	
	@RequestMapping(value = "/services/business/posted/filter", method = RequestMethod.POST)
	public List<BusinessJoinModel> getBusinessFilter(@Valid @RequestBody BusinessFilterRequest request) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Business Filter service is invoked.");
		return service.getAllBusinessFilter(request.getCatId());
	}
	
	@RequestMapping(value = "/services/business/posted/{businessId}", method = RequestMethod.GET)
	public Optional<BusinessJoinModel> getBusinessById(@PathVariable Integer businessId) {
		System.out.println(this.getClass().getSimpleName() + " - GET Business by Ad ID service is invoked.");
		return service.getBusinessById(businessId);
	}
	
	@RequestMapping(value = "/services/business/posted/seller/{userId}", method = RequestMethod.GET)
	public List<BusinessJoinModel> getBusinessBySeller(@Valid @PathVariable Long userId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Business service is invoked.");
		return service.getBusinessByUser(userId);
	}
	
	
	@RequestMapping(value = "/services/business/posted", method = RequestMethod.POST)
	public BusinessMasterModel createAd(@Valid @RequestBody BusinessRequest request,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Create new Post method is invoked.");
		
		UserModel user = identity.get(authentication);
		
		BusinessMasterModel business = new BusinessMasterModel();
		business.setAboutBusiness(request.getAboutBusiness());
		business.setAddressLine1(request.getAddressLine1());
		business.setAddressLine2(request.getAddressLine2());
		business.setBannerImage(request.getBannerImage());
		business.setBusinessName(request.getBusinessName());
		business.setCatId(request.getCatId());
		business.setCity(request.getCity());
		business.setContact1(request.getContact1());
		business.setContact2(request.getContact2());
		business.setCountry(request.getCountry());
		business.setCreatedBy(user.getId());
		business.setUpdatedBy(user.getId());
		business.setEmail(request.getEmail());
		business.setFacebook(request.getFacebook());
		business.setLinkedIn(request.getLinkedIn());
		business.setPinCode(request.getPinCode());
		business.setSkype(request.getSkype());
		business.setState(request.getState());
		business.setTelegram(request.getTelegram());
		business.setTwitter(request.getTwitter());
		business.setWebsite(request.getWebsite());
		business.setWhatsapp(request.getWhatsapp());
		business.setWechat(request.getWechat());
		
		
		BusinessMasterModel created =  service.createNewBusiness(business);
		
		request.getFacility().forEach(fId -> {
			BusinessFacilityModel fac = new BusinessFacilityModel();
			fac.setFacilityId(fId);
			fac.setBusinessId(created.getId());
			service.BusinessFacility(fac);
		});
		
		request.getMedia().forEach(murl -> {
			BusinessMediaModel media = new BusinessMediaModel();
			media.setBusinessId(created.getId());
			media.setUrl(murl);
			service.BusinessMedia(media);
		});
		
		return created;
	}
	
	@RequestMapping(value = "/services/business/posted/{adId}", method = RequestMethod.DELETE)
	public BusinessJoinModel deleteBusiness(@PathVariable Integer adId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete Business service is invoked.");
		return service.deleteBusiness(adId);
	}
	
	@RequestMapping(value = "/services/business/wishlist/{userMasterId}", method = RequestMethod.GET)
	public List<BusinessUserWishListModel> getAdWishList(@PathVariable Long userMasterId,Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - GET Linked ad to wishlist for user ");
		
		identity.checkIfResourceAllowedForRole(userMasterId, authentication);
		
		return service.getWishlist(userMasterId);
	
	}

	
	@RequestMapping(value = "/services/business/wishlist/{businessId}", method = RequestMethod.PATCH)
	public BusinessUserWishListModel adWishList(@PathVariable Integer businessId,Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Link ad to wishlist for user ");
		
		UserModel user = identity.get(authentication);

		BusinessUserWishListModel wishlist = new BusinessUserWishListModel();
		wishlist.setBusinessId(businessId);
		wishlist.setUserMasterId(user.getId());
		wishlist.setCreatedBy(user.getId());
		wishlist.setUpdatedBy(user.getId());
		wishlist.setStatus(true);
		return service.wishlist(wishlist);
	
	}
	
	@RequestMapping(value = "/services/business/report", method = RequestMethod.POST)
	public BusinessReportModel businessReport(@Valid @RequestBody BusinessReportRequest request, Authentication authentication) throws Exception{
		System.out.println(this.getClass().getSimpleName() + " - Link ad to wishlist for user ");
		
		UserModel user = identity.get(authentication);
		
		BusinessReportModel report = new BusinessReportModel();
		
		report.setBusinessId(request.getBusinessId());
		report.setUserMasterId(request.getUserMasterId());
		report.setReason(request.getReason());
		report.setCreatedBy(user.getId());
		report.setUpdatedBy(user.getId());
		
		return service.createReport(report);	
		
	}
	
	@RequestMapping(value = "/services/business/similar/{businessId}/catId/{catId}", method = RequestMethod.GET)
	public List<BusinessJoinModel> getSimilarAds(@PathVariable Integer businessId,@PathVariable Integer catId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Similar Business service is invoked.");
		return service.getSimilarBusiness(businessId,catId);
	}
	
	@RequestMapping(value = "/services/business/latest/nearBy", method = RequestMethod.GET)
	public List<BusinessJoinModel> getNearByBusiness(@RequestParam String location) {
		System.out.println(this.getClass().getSimpleName() + " - Get all NearBy Business service is invoked.");
		return service.getNearByBusiness(location);
	}
	
	@RequestMapping(value = "/services/business/search", method = RequestMethod.GET)
	public List<BusinessJoinModel> getBusinessSearch(@RequestParam String key) {
		System.out.println(this.getClass().getSimpleName() + " - Get all NearBy Business service is invoked.");
		return service.getBusinessSearch(key);
	}
	
	
	/**
	 * 
	 * @param catId
	 * @return
	 */
	public BusinessCategoryModel getFirstLevelCat(Integer catId) {
		BusinessCategoryModel cm = servicecat.getCatById(catId).get();
		if(cm.getParentCatId() !=null) {
			return this.getFirstLevelCat(cm.getParentCatId());
		}else {
			return cm;
		}
		
		//System.out.println(cm.getParentCatId());
		
	}
	
}
