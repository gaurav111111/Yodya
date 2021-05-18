package com.yupexx.services.api.controller;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.BlogModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.BusinessDetailMapping;
import com.yupexx.services.api.model.BusinessCategoryModel;
import com.yupexx.services.api.model.ServicesCategoryDetails;
import com.yupexx.services.api.model.ServicesCategoryLabel;
import com.yupexx.services.api.model.business.BusinessFacilityModel;
import com.yupexx.services.api.model.business.BusinessJoinModel;
import com.yupexx.services.api.model.business.BusinessMasterModel;
import com.yupexx.services.api.model.business.BusinessMediaModel;
import com.yupexx.services.api.model.business.BusinessReportModel;
import com.yupexx.services.api.model.business.BusinessReviewJoinModel;
import com.yupexx.services.api.model.business.BusinessUserWishListModel;
import com.yupexx.services.api.model.dto.BusinessCatLabelDTO;
import com.yupexx.services.api.model.dto.BusinessCatListDTO;
import com.yupexx.services.api.model.dto.BusinessMappingDTO;
import com.yupexx.services.api.model.dto.BusinessMasterDTO;
import com.yupexx.services.api.model.view.BusinessCategoryVwModel;
import com.yupexx.services.api.repository.BusinessCategoryVwRepository;
import com.yupexx.services.api.repository.BusinessJoinRepository;
import com.yupexx.services.api.repository.BusinessMediaRepository;
import com.yupexx.services.api.repository.BusinessReviewJoinRepository;
import com.yupexx.services.api.repository.BussinessCategoryDetailMappingRepository;
import com.yupexx.services.api.repository.ServiceCategoryDetailRepository;
import com.yupexx.services.api.repository.ServicesCategoryLabelRepository;
import com.yupexx.services.api.request.BusinessFilterRequest;
import com.yupexx.services.api.request.BusinessReportRequest;
import com.yupexx.services.api.request.BusinessRequest;
import com.yupexx.services.api.service.BusinessCategoryService;
import com.yupexx.services.api.service.BusinessService;

@RestController
@CrossOrigin
public class BusinessController {

	@Autowired
	BusinessService service;
	
	@Autowired
	BusinessCategoryService servicecat;
	
	@Autowired
	UserIdentity identity;
	
	@Autowired
	BusinessJoinRepository daoJoin;
	
	@Autowired
	BusinessCategoryVwRepository daoVw;
	
	@Autowired
	BussinessCategoryDetailMappingRepository busCatDetMapping;
	
	@Autowired
	ServiceCategoryDetailRepository dao;
	
	@Autowired
	BusinessMediaRepository daoMedia;
	
	@Autowired
	ServicesCategoryLabelRepository labelRepo;
	
	@Autowired 
	ServiceCategoryDetailRepository detailsRepo;
	
	@Autowired
	BusinessReviewJoinRepository daoReview;

	@RequestMapping(value = "/services/business/posted", method = RequestMethod.GET)
	public List<BusinessJoinModel> getBusiness() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Business service is invoked.");
		return service.getAllBusiness();
	}
	

	@RequestMapping(value = "/services/business/count", method = RequestMethod.GET)
	public List<BusinessCatListDTO> getBusinessCount() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Business service is invoked.");
		 List<BusinessJoinModel> bjm=daoJoin.findByStatus(true);
		 List<BusinessCatListDTO> bcd = new ArrayList<BusinessCatListDTO>();
		 List<BusinessCategoryVwModel> busCatData=daoVw.findAll();
		 
		 bjm.forEach((n) -> System.out.println("CategoryId ****"+n.getCatId())); 
		 Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		 for (BusinessJoinModel i : bjm)
		    {
		        Integer retrievedValue = map.get(i.getCatId());
		        if (null == retrievedValue)
		        {
		            map.put(i.getCatId(), 1);
		        }
		        else
		        {
		            map.put(i.getCatId(), retrievedValue + 1);
		        }
		    }
		 
		 System.out.println("CategoryId bcd ****"+map.toString()); 
		 for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
			 for(BusinessCategoryVwModel busCat:busCatData) {
				 if(entry.getKey()==busCat.getId()) { 
					 for(BusinessCategoryVwModel busCat1:busCatData) {
						 if(busCat.getParentCatId()==busCat1.getId()) {
							 
							 int flag=0;
							 
							 for(BusinessCatListDTO c:bcd) {
								 if(c.getParentCatId()==busCat.getParentCatId()) {
									 flag=1;
									 c.setTotal(c.getTotal()+entry.getValue());
								 }
							 }
							 if(flag==0) {
								 BusinessCatListDTO busCatDto = new BusinessCatListDTO();
								 busCatDto.setCatId(entry.getKey());
								 busCatDto.setParentCatId(busCat1.getId());
								 busCatDto.setCategoryName(busCat1.getCategoryName());
								 busCatDto.setTotal(entry.getValue());
								 bcd.add(busCatDto); 
							 }
							
						 }
					 }
					 
				 }
			 }
			 
		 }
		 
		 bcd.forEach((n) -> System.out.println("CategoryId bcd ****"+n.toString())); 
		 
		 
		return bcd;
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
	public BusinessMasterDTO getBusinessById(@PathVariable Integer businessId) {
		System.out.println(this.getClass().getSimpleName() + " - GET Business by Ad ID service is invoked.");
		return service.getBusinessById(businessId);
	}
	
	
	@RequestMapping(value = "/services/business/posted/seller/{userId}", method = RequestMethod.GET)
	public List<BusinessJoinModel> getBusinessBySeller(@Valid @PathVariable Long userId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Business service is invoked.");
		return service.getBusinessByUser(userId);
	}
	
	
	@RequestMapping(value = "/services/business/posted", method = RequestMethod.POST)
	public BusinessMasterDTO createAd(@Valid @RequestBody BusinessRequest request,Authentication authentication) throws Exception {
		Message msg = new Message();
		System.out.println(this.getClass().getSimpleName() + " - Create new Post method is invoked.");
		
		if(authentication==null) {
			throw new Exception("JWT Token Required");
		}
		UserModel user = identity.get(authentication);
		
		if(user==null) {
			throw new Exception("User Not found from JWT Token");
		}
		
		System.out.println("user$$$"+user.toString());
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
		if(request.getFacility().size()>0) {
			for(Integer a :request.getFacility() ) {
		           BusinessDetailMapping fac = new BusinessDetailMapping();
				//	Optional<ServicesCategoryDetails> sc = dao.findById(a);
					ServicesCategoryDetails object = dao.findById(a).orElseThrow(() -> new Exception("Category Details Not exist with this ID:"+a) );
					//System.out.println("sc.get()"+sc.get());
					fac.setCategorydetailId(a);
					fac.setStatus(true);
					fac.setCreatedBy(user.getId());
					fac.setBusinessId(created.getId());
					busCatDetMapping.save(fac);	
				}	
		}
		request.getMedia().forEach(murl -> {
			BusinessMediaModel media = new BusinessMediaModel();
			media.setBusinessId(created.getId());
			media.setUrl(murl);
			service.BusinessMedia(media);
		});
		BusinessMasterDTO bmt = new BusinessMasterDTO();
		bmt.setId(created.getId());
		bmt.setCatId(created.getCatId());
		bmt.setBusinessName(created.getBusinessName());
		bmt.setAboutBusiness(created.getAboutBusiness());
		bmt.setBannerImage(created.getBannerImage());
		bmt.setCountry(created.getCountry());
		bmt.setPinCode(created.getPinCode());
		bmt.setState(created.getState());
		bmt.setCity(created.getCity());
		bmt.setAddressLine1(created.getAddressLine1());
		bmt.setAddressLine2(created.getAddressLine2());
		bmt.setWebsite(created.getWebsite());
		bmt.setContact1(created.getContact1());
		bmt.setContact2(created.getContact2());
		bmt.setWhatsapp(created.getWhatsapp());
		bmt.setWechat(created.getWechat());
		bmt.setTelegram(created.getTelegram());
		bmt.setSkype(created.getSkype());
		bmt.setFacebook(created.getFacebook());
		bmt.setLinkedIn(created.getLinkedIn());
		bmt.setEmail(created.getEmail());
		bmt.setIsVerified(created.getIsVerified());
		bmt.setStatus(created.getStatus());
		bmt.setCreatedBy(created.getCreatedBy());
		bmt.setUpdatedBy(created.getUpdatedBy());
		bmt.setCreatedDate(created.getCreatedDate());
		bmt.setUpdatedDate(created.getUpdatedDate());
		List<BusinessMediaModel> media =daoMedia.findByBusinessId(created.getId());
		if(media!=null && media.size()>0) {
			bmt.setMedia(media);
		}
		List<BusinessReviewJoinModel> review= daoReview.findByBusinessId(created.getId());
		bmt.setReview(review);
		 List<BusinessDetailMapping> bdm = busCatDetMapping.findByBusinessId(created.getId());
		 List<BusinessCatLabelDTO> labelList1 = new ArrayList<>();
		 if(bdm!=null && bdm.size()>0) {
			 for(BusinessDetailMapping b: bdm) {
				 List<ServicesCategoryDetails> detailsList = new ArrayList<ServicesCategoryDetails>();
				 System.out.println("b.getCategorydetailId()"+b.getCategorydetailId());
				 Optional<ServicesCategoryDetails> details=detailsRepo.findById(b.getCategorydetailId());
				 detailsList.add(details.get());
				 System.out.println("details.getLabelId()"+details.get().getLabelId());
				 Optional<ServicesCategoryLabel> labelList=labelRepo.findById(details.get().getLabelId());
						BusinessCatLabelDTO bclData = new BusinessCatLabelDTO();
						bclData.setDetailsData(detailsList);
						bclData.setId(labelList.get().getId());
						bclData.setCategoryId(labelList.get().getCategoryId());
						bclData.setCreatedBy(labelList.get().getCreatedBy());
						bclData.setCreatedDate(labelList.get().getCreatedDate());
						bclData.setStatus(labelList.get().getStatus());
						bclData.setValue(labelList.get().getValue());
						bclData.setUpdatedBy(labelList.get().getUpdatedBy());
						bclData.setUpdatedDate(labelList.get().getUpdatedDate());
						labelList1.add(bclData);
			 }
			 bmt.setLabel(labelList1);
		 }
		msg.setCode(200);
		msg.setContentList(bmt);
		return bmt;
		
	}
	
	
	
	
	@RequestMapping(value = "/services/business/posted/{adId}", method = RequestMethod.DELETE)
	public Message deleteBusiness(@PathVariable Integer adId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete Business service is invoked.");
		Message msg = new Message();
		Optional<BusinessJoinModel> data =daoJoin.findById(adId);
		if(data.get()!=null) {
			if(data.get().getStatus()) {
				data.get().setStatus(false);
				daoJoin.save(data.get());
				msg.setCode(200);
				msg.setMessage("InActive Sucessfully!");
				return msg;
			}else {
				data.get().setStatus(true);
				daoJoin.save(data.get());
				msg.setCode(200);
				msg.setMessage("Active Sucessfully!");
				return msg;
			}
			
		}else {
			msg.setCode(400);
			msg.setMessage("Add not found by this Id");
			return msg;
		}
	}
	
	@RequestMapping(value = "/services/business/wishlist/{userMasterId}", method = RequestMethod.GET)
	public List<BusinessUserWishListModel> getAdWishList(@PathVariable Long userMasterId) {
		System.out.println(this.getClass().getSimpleName() + " - GET Linked ad to wishlist for user ");
		//identity.checkIfResourceAllowedForRole(userMasterId, authentication);
		return service.getWishlist(userMasterId);
	
	}

	
	@RequestMapping(value = "/services/business/wishlist/{businessId}", method = RequestMethod.PATCH)
	public BusinessUserWishListModel adWishList(@PathVariable Integer businessId,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Link ad to wishlist for user ");
		if(authentication==null) {
			throw new Exception("JWT Token Required");
		}
		UserModel user = identity.get(authentication);
		if(user==null) {
			throw new Exception("User Not found by this Token");
		}
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
	
	@RequestMapping(value = "/sevices/business/similar/{businessId}/catId/{catId}", method = RequestMethod.GET)
	public List<BusinessJoinModel> getSimilarAds(@PathVariable Integer businessId,@PathVariable Integer catId,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get all Similar Business service is invoked.");
		if(authentication==null) {
			throw new Exception("JWT Token Required");
		}
		UserModel user = identity.get(authentication);
		if(user==null) {
			throw new Exception("User Not found by this Token");
		}
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
