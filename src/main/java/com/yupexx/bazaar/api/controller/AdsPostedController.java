package com.yupexx.bazaar.api.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.ads.AdPostAccessoryModel;
import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.BazaarDetailMapping;
import com.yupexx.bazaar.api.model.CategoryDetailsModel;
import com.yupexx.bazaar.api.model.CategoryLable;
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
import com.yupexx.bazaar.api.model.dto.AdPostUserResponseDto;
import com.yupexx.bazaar.api.model.dto.AddPostDTO;
import com.yupexx.bazaar.api.model.dto.BazaarLabelDto;
import com.yupexx.bazaar.api.model.dto.LabelIdDTO;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.model.view.CategoryAdVwModel;
import com.yupexx.bazaar.api.repository.AdPostDealRepository;
import com.yupexx.bazaar.api.repository.AdPostUserSubscriptionRepository;
import com.yupexx.bazaar.api.repository.BazaarDetailMappingRepository;
import com.yupexx.bazaar.api.repository.CategoryDetailRepository;
import com.yupexx.bazaar.api.repository.CategoryLabelRepository;
import com.yupexx.bazaar.api.service.AdPostedService;
import com.yupexx.bazaar.api.service.UserService;
import com.yupexx.bazaar.api.service.interfaces.CategoryService;
import com.yupexx.services.api.model.dto.PostLabelMapDto;

@RestController
@CrossOrigin
public class AdsPostedController {

	@Autowired
	AdPostedService service;
	
	@Autowired
	CategoryService servicecat;
	
	@Autowired
	UserIdentity identity;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdPostDealRepository daoDeal;
	
	@Autowired
	AdPostUserSubscriptionRepository  daoSub;
	
	@Autowired
	BazaarDetailMappingRepository  bazaaraddao;
	
	@Autowired
	private CategoryLabelRepository categoryLabelRepository;
	
	@Autowired
	CategoryDetailRepository dao;

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
	public AdPostUserResponseDto getAdPostedById(@PathVariable Integer adId) {
		System.out.println(this.getClass().getSimpleName() + " - GET AdPosted by Ad ID service is invoked.");
		return service.getAdPostedById(adId);
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/seller/{sellerId}", method = RequestMethod.GET)
	public List<AdPostUserJoinModel> getAdPostedBySeller(@Valid @PathVariable Long sellerId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all AdPosted service is invoked.");
		return service.getAdPostedBySeller(sellerId);
	}
	
	@RequestMapping(value = "/bazaar/ads", method = RequestMethod.PUT)
	public Message extendDate(@RequestBody AddPostDTO adp) {
		System.out.println(this.getClass().getSimpleName() + " - Get all AdPosted service is invoked.");
		Message msg = new Message();
		
		Optional<AdPostUserSubscriptionsModel> subscription = daoSub.findById(adp.getId());
		System.out.println("subscription"+subscription.toString());
	if(subscription.get()!=null) {
	//	Timestamp timestamp = null;
		try {
			System.out.println("adp.getExpiryDate()"+adp.getExpiryDate());
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
			Date parsedDate = inputFormat.parse(adp.getExpiryDate());
			String formattedDate = outputFormat.format(parsedDate);
			
			Timestamp ts= new Timestamp(parsedDate.getTime());
		    subscription.get().setExpiryDate(ts);
		    System.out.println("timestamp"+ts);
			daoSub.save(subscription.get());
		} catch(Exception e) { 
			
		}
		msg.setCode(200);
		msg.setMessage("Date updated");
		return msg;
	}else {
		msg.setCode(400);
		msg.setMessage("Date not updated");
		return msg;
	}
		
	}
	
	@RequestMapping(value = "/bazaar/ads/posted", method = RequestMethod.POST)
	public AdPostUserResponseDto createAd(@Valid @RequestBody AdPostUserDTO postAd,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Create new Post method is invoked.");
		
		if(authentication==null) {
			throw new Exception("JWT Token Required");
		}
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
		
		if(postAd.getProductModelName()==null) {
			creatAd.setProductModelName("");
		}else {
			creatAd.setProductModelName(postAd.getProductModelName());
		}
		
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
		
		if(postAd.getLabel().size()>0) {
			for(PostLabelMapDto pl: postAd.getLabel()) {
				Optional<CategoryLable> cl = categoryLabelRepository.findById(pl.getLabelId());
				if(cl.get()==null) {
					throw new Exception("Label not avaliable from this id:"+pl.getLabelId());
				}
				if(pl.getCatDetailsId().size()>0) {
					for(int i=0; i<pl.getCatDetailsId().size(); i++) {
						Optional<CategoryDetailsModel> cd = dao.findById(pl.getCatDetailsId().get(i));
						if(cd.get()==null) {
							throw new Exception("Category Details not avaliable from this id:"+pl.getLabelId());
						}
						BazaarDetailMapping media = new BazaarDetailMapping();
						media.setAdId(created.getId());
						media.setLabelId(pl.getLabelId());
						media.setCategorydetailId(pl.getCatDetailsId().get(i));
						bazaaraddao.save(media);
				}
				}
				else {
					BazaarDetailMapping media = new BazaarDetailMapping();
					media.setAdId(created.getId());
					media.setLabelId(pl.getLabelId());
					bazaaraddao.save(media);
				}
			}	
		}
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
		AdPostUserResponseDto response = new AdPostUserResponseDto();
		response.setId(created.getId());
		response.setCatId(created.getCatId());
		response.setCatMediaId(created.getCatMediaId());
		response.setAdTitle(created.getAdTitle());
		response.setAdDescription(created.getAdDescription());
		response.setAdPrice(created.getAdPrice());
		response.setAdCurrency(created.getAdCurrency());
		response.setLocation(created.getLocation());
		response.setLatitude(created.getLatitude());
		response.setLongitude(created.getLongitude());
		response.setProductCondition(created.getProductCondition());
		response.setManufacturedYear(created.getManufacturedYear());
		response.setProductModelName(created.getProductModelName());
		response.setStatus(created.getStatus());
		response.setCreatedDate(created.getCreatedDate());
		response.setCreatedBy(created.getCreatedBy());
		response.setUpdatedBy(created.getUpdatedBy());
		response.setUpdatedDate(created.getUpdatedDate());
		List<LabelIdDTO> bdm=bazaaraddao.findDistinctLabelByAdid(created.getId());
		
		List<BazaarLabelDto> labeList = new ArrayList<BazaarLabelDto>();
		for(LabelIdDTO b:bdm) {
			System.out.println(b.getlabelID()+"label------------------");
			Optional<CategoryLable> cl =categoryLabelRepository.findById(b.getlabelID());
			BazaarLabelDto blt = new BazaarLabelDto();
			blt.setId(cl.get().getId());
			blt.setCategoryId(cl.get().getCategoryId());
			blt.setValue(cl.get().getValue());
			blt.setStatus(cl.get().getStatus());
			List<LabelIdDTO> catId=bazaaraddao.findDistinctCatByAdid(b.getlabelID());
			List<CategoryDetailsModel> cdmList = new ArrayList<CategoryDetailsModel>();
			for(LabelIdDTO c:catId) {
				Optional<CategoryDetailsModel> cdm = dao.findById(c.getlabelID());
				cdmList.add(cdm.get());
			}
			blt.setCategoryDetailList(cdmList);
			labeList.add(blt);
		}
		
		response.setLabeList(labeList);
		
		return response;
	}
	
	@RequestMapping(value = "/bazaar/ads/posted/{adId}", method = RequestMethod.DELETE)
	public AdPostUserModel deleteAdPosted(@PathVariable Integer adId, Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete AdPosted service is invoked.");
		if(!userService.userRoleCheck(authentication).equalsIgnoreCase("admin")) {
			throw new Exception("Only Admin can delete Post.");
		}
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
	
	
	@RequestMapping(value = "/bazaar/ads/deals", method = RequestMethod.POST)
	public Message addDeal(@RequestBody AdPostDealModel adpost) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Deals AdPosted service is invoked.");
		Message msg  = new Message();
		AdPostDealModel save=service.Update(adpost);
		msg.setCode(200);
		msg.setMessage("Save Sucessfully");
		return msg;
	}
	
	@RequestMapping(value = "/bazaar/ads/deals/update", method = RequestMethod.PUT)
	public Message updateDeal(@RequestBody AdPostDealModel adpost) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Deals AdPosted service is invoked.");
		Message msg  = new Message();
		Optional<AdPostDealModel> fetch=service.findById(adpost.getId());
		if(fetch.get()!=null) {
			fetch.get().setDealName(adpost.getDealName());
			fetch.get().setDealDescription(adpost.getDealDescription());
			fetch.get().setDealPrice(adpost.getDealPrice());
			fetch.get().setDealPeriod(adpost.getDealPeriod());
			AdPostDealModel save=service.Update(fetch.get());
			msg.setCode(200);
			msg.setMessage("Update Sucessfully!");
			return msg;
		}else {
			msg.setCode(400);
			msg.setMessage("Deal Not found!");
			return msg;
		}
		
	}
	
	@RequestMapping(value = "/bazaar/ads/deal/{dealId}", method = RequestMethod.DELETE)
	public Message deleteDeal(@PathVariable Integer dealId,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " -  AdPosted Delete service is invoked.");
		Message msg = new Message();
		if(!userService.userRoleCheck(authentication).equalsIgnoreCase("admin")) {
			//throw new Exception("Only Admin can delete category.");
			    msg.setCode(400);
		        msg.setMessage("Only Admin can delete Deals.");
		        return msg;
			
		}else {
			Optional<AdPostDealModel> adpost = service.findById(dealId);
			if(adpost.get()!=null) {
				AdPostDealModel adpost1 =adpost.get();
				System.out.println("deal status"+adpost1.getStatus());
				if(adpost1.getStatus()) {
					adpost1.setStatus(false);
					service.deleteDeal(adpost1);
					   msg.setCode(200);
				        msg.setMessage("Deal Inactive Sucessfully");
				        return msg;
				}else {
					adpost1.setStatus(true);
					service.deleteDeal(adpost1);
					msg.setCode(200);
			        msg.setMessage("Deal Active Sucessfully");
			        return msg;
				}
			}else {
				 msg.setCode(400);
			        msg.setMessage("Only Admin can delete Deals.");
			        return msg;
			}
		}
		
		//return service.getAllDeals();
		
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
