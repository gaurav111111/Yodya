package com.yupexx.bazaar.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryDetailsModel;
import com.yupexx.bazaar.api.model.CategoryLable;
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
import com.yupexx.bazaar.api.model.dto.AdPostUserResponseDto;
import com.yupexx.bazaar.api.model.dto.BazaarLabelDto;
import com.yupexx.bazaar.api.model.dto.LabelIdDTO;
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
import com.yupexx.bazaar.api.repository.BazaarDetailMappingRepository;
import com.yupexx.bazaar.api.repository.CategoryDetailRepository;
import com.yupexx.bazaar.api.repository.CategoryLabelRepository;
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
	
	@Autowired
	BazaarDetailMappingRepository  bazaaraddao;
	
	@Autowired
	private CategoryLabelRepository categoryLabelRepository;
	
	@Autowired
	CategoryDetailRepository dao1;
	
	@Override
	public List<AdPostUserJoinModel> getAdPosted() {
		// TODO Auto-generated method stub
		 System.out.println("Posted Details");
		List<AdPostUserJoinModel> data = daoVw.findTop12ByStatusOrderByUpdatedDateDesc(true);
	//	List<AdPostUserJoinModel> data = daoVw.findTop12OrderByUpdatedDateDesc();
	    System.out.println("Posted Details");
		System.out.println(data.toString());
		return data;
	}
	
	@Override
	public List<AdPostUserJoinModel> getFeaturedAdPosted() {
		// TODO Auto-generated method stub
		//return daoVw.findTop12ByAdPriceGreaterThanAndStatusOrderByUpdatedDateDesc(0);
		return daoVw.findTop12ByAdPriceGreaterThanOrderByUpdatedDateDesc(0);
	}
	
	@Override
	public AdPostUserModel deleteAdPosted(Integer adId) {
		// TODO Auto-generated method stub
		AdPostUserModel object = dao.findOneByIdAndStatus(adId, true);
		if(object==null) {
			throw new EntityNotFoundException("Ad Post not found");
		}else {
			if(object.getStatus()) {
				object.setStatus(false);
			}else {
				object.setStatus(true);
			}
		}
		
		return dao.save(object);
	}
	
	@Override
	public AdPostUserResponseDto getAdPostedById(Integer adId) {
		// TODO Auto-generated method stub
		Optional<AdPostUserJoinModel> created = daoVw.findById(adId);
		
		
		AdPostUserResponseDto response = new AdPostUserResponseDto();
		response.setId(created.get().getId());
		response.setCatId(created.get().getCatId());
		response.setAdTitle(created.get().getAdTitle());
		response.setAdDescription(created.get().getAdDescription());
		response.setAdPrice(created.get().getAdPrice());
		response.setAdCurrency(created.get().getAdCurrency());
		response.setLocation(created.get().getLocation());
		response.setLatitude(created.get().getLatitude());
		response.setLongitude(created.get().getLongitude());
		response.setProductCondition(created.get().getProductCondition());
		response.setManufacturedYear(created.get().getManufacturedYear());
		response.setProductModelName(created.get().getProductModelName());
		response.setStatus(created.get().getStatus());
		response.setCreatedDate(created.get().getCreatedDate());
		response.setCreatedBy(created.get().getCreatedBy());
		response.setUpdatedBy(created.get().getUpdatedBy());
		response.setUpdatedDate(created.get().getUpdatedDate());
		
		List<AdPostMediaModel> media = daoMedia.findByAdId(created.get().getId());
		response.setMedia(media);
		List<LabelIdDTO> bdm=bazaaraddao.findDistinctLabelByAdid(created.get().getId());
		
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
				Optional<CategoryDetailsModel> cdm = dao1.findById(c.getlabelID());
				cdmList.add(cdm.get());
			}
			blt.setCategoryDetailList(cdmList);
			labeList.add(blt);
		}
		
		response.setLabeList(labeList);
		
		
		return response;
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdPostedBySeller(Long sellerId) {
		// TODO Auto-generated method stub
		return daoVw.findByCreatedByAndStatusOrderByUpdatedDateDesc(sellerId, true);
		//return daoVw.findByCreatedByOrderByUpdatedDateDesc(sellerId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdPostedByFilter(List<Integer> catId) {
		// TODO Auto-generated method stub
		return daoVw.findByCatIds(catId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getSimilarAds(Integer adId,Integer catId) {
		// TODO Auto-generated method stub
		//return daoVw.findByStatusAndCatIdAndIdNotOrderByUpdatedDateDesc(true,catId,adId);
		return daoVw.findByCatIdAndIdNotOrderByUpdatedDateDesc(catId,adId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdByCatId(Integer catId) {
		// TODO Auto-generated method stub
		return daoVw.findByStatusAndCatIdOrderByUpdatedDateDesc(true,catId);
	}
	
	@Override
	public List<AdPostUserJoinModel> getNearByAds(String location) {
		// TODO Auto-generated method stub
	//	return daoVw.findByStatusAndLocationContainingOrderByUpdatedDateDesc(true,location);
		return daoVw.findByLocationContainingOrderByUpdatedDateDesc(location);
	}
	
	@Override
	public List<AdPostUserJoinModel> getAdSearch(String key) {
		// TODO Auto-generated method stub
	//	return daoVw.findByStatusAndAdTitleContainingOrAdDescriptionContainingOrderByUpdatedDateDesc(true,key,key);
		return daoVw.findByAdTitleContainingOrAdDescriptionContainingOrderByUpdatedDateDesc(key,key);
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
		//return daoDeal.findByStatus(true);
		return daoDeal.findAll();
	}
	
	@Override
	public Optional<AdPostDealModel> getDealById(Integer dealId) {
		// TODO Auto-generated method stub
		return daoDeal.findByIdAndStatus(dealId,true);
	}

	public Optional<AdPostDealModel> findById(Integer dealId) {
		// TODO Auto-generated method stub
		return daoDeal.findById(dealId);
	}
	
	public AdPostDealModel Update(AdPostDealModel adPostDealModel) {
		// TODO Auto-generated method stub
		adPostDealModel.setCreatedBy((long)1);
		adPostDealModel.setStatus(true);
		adPostDealModel.setUpdatedBy((long)1);
		return daoDeal.save(adPostDealModel);
	}
	
	public AdPostDealModel deleteDeal(AdPostDealModel adPostDealModel) {
		// TODO Auto-generated method stub
		
		return daoDeal.save(adPostDealModel);
	}
}
