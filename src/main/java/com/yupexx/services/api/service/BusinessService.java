package com.yupexx.services.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.BusinessDetailMapping;
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
import com.yupexx.services.api.model.dto.BusinessJoinModelDTO;
import com.yupexx.services.api.model.dto.BusinessMappingDTO;
import com.yupexx.services.api.model.dto.BusinessMasterDTO;
import com.yupexx.services.api.repository.BusinessFacilityRepository;
import com.yupexx.services.api.repository.BusinessJoinRepository;
import com.yupexx.services.api.repository.BusinessMediaRepository;
import com.yupexx.services.api.repository.BusinessReportRepository;
import com.yupexx.services.api.repository.BusinessRepository;
import com.yupexx.services.api.repository.BusinessReviewJoinRepository;
import com.yupexx.services.api.repository.BusinessUserWishListRepository;
import com.yupexx.services.api.repository.BussinessCategoryDetailMappingRepository;
import com.yupexx.services.api.repository.ServiceCategoryDetailRepository;
import com.yupexx.services.api.repository.ServicesCategoryLabelRepository;
import com.yupexx.services.api.service.interfaces.BusinessInterface;

@Service
public class BusinessService implements BusinessInterface  {

	@Autowired
	BusinessRepository dao;
	
	@Autowired
	BusinessJoinRepository daoJoin;
	
	@Autowired
	BusinessFacilityRepository daoFacility;
	
	@Autowired
	BusinessMediaRepository daoMedia;
	
	@Autowired
	BusinessUserWishListRepository daoWishlist;
	
	@Autowired
	BusinessReportRepository daoReport;
	
	@Autowired
	BussinessCategoryDetailMappingRepository busCatDetMapping;
	
	
	@Autowired 
	ServiceCategoryDetailRepository detailsRepo;
	
	@Autowired
	ServicesCategoryLabelRepository labelRepo;
	
	@Autowired
	BusinessReviewJoinRepository daoReview;

	@Override
	public List<BusinessJoinModel> getAllBusiness() {
		// TODO Auto-generated method stub
		return daoJoin.findByStatus(true);
	}
	
	
	
	@Override
	public List<BusinessJoinModel> getSponsoredBusiness() {
		// TODO Auto-generated method stub
		return daoJoin.findTop5ByStatusOrderByUpdatedDateDesc(true);
	}
	
	@Override
	public List<BusinessJoinModel> getAllBusinessFilter(List<Integer> catId) {
		// TODO Auto-generated method stub
		return daoJoin.findByCatIds(catId);
	}

	@Override
	public BusinessMasterDTO getBusinessById(Integer businessId) {
		// TODO Auto-generated method stub
		BusinessMasterDTO bmt = new BusinessMasterDTO();
		Optional<BusinessJoinModel> data= daoJoin.findByIdAndStatusOrderByUpdatedDateDesc(businessId,true);
		
		bmt.setId(data.get().getId());
		bmt.setCatId(data.get().getCatId());
		bmt.setBusinessName(data.get().getBusinessName());
		bmt.setAboutBusiness(data.get().getAboutBusiness());
		bmt.setBannerImage(data.get().getBannerImage());
		bmt.setCountry(data.get().getCountry());
		bmt.setPinCode(data.get().getPinCode());
		bmt.setState(data.get().getState());
		bmt.setCity(data.get().getCity());
		bmt.setAddressLine1(data.get().getAddressLine1());
		bmt.setAddressLine2(data.get().getAddressLine2());
		bmt.setWebsite(data.get().getWebsite());
		bmt.setContact1(data.get().getContact1());
		bmt.setContact2(data.get().getContact2());
		bmt.setWhatsapp(data.get().getWhatsapp());
		bmt.setWechat(data.get().getWechat());
		bmt.setTelegram(data.get().getTelegram());
		bmt.setSkype(data.get().getSkype());
		bmt.setFacebook(data.get().getFacebook());
		bmt.setLinkedIn(data.get().getLinkedIn());
		bmt.setEmail(data.get().getEmail());
		bmt.setIsVerified(data.get().getIsVerified());
		bmt.setStatus(data.get().getStatus());
		bmt.setCreatedBy(data.get().getCreatedBy());
		bmt.setUpdatedBy(data.get().getUpdatedBy());
		bmt.setCreatedDate(data.get().getCreatedDate());
		bmt.setUpdatedDate(data.get().getUpdatedDate());
		List<BusinessMediaModel> media =daoMedia.findByBusinessId(data.get().getId());
		if(media!=null && media.size()>0) {
			bmt.setMedia(media);
		}
		
		List<BusinessReviewJoinModel> review= daoReview.findByBusinessId(data.get().getId());
		bmt.setReview(review);
		 List<BusinessDetailMapping> bdm = busCatDetMapping.findByBusinessId(data.get().getId());
		 List<BusinessCatLabelDTO> labelList1 = new ArrayList<>();
		 if(bdm!=null && bdm.size()>0) {
			 for(BusinessDetailMapping b: bdm) {
				 List<ServicesCategoryDetails> detailsList = new ArrayList<ServicesCategoryDetails>();
				
				 Optional<ServicesCategoryDetails> details=detailsRepo.findById(b.getCategorydetailId());
				 detailsList.add(details.get());
				
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
		return bmt;
	}
	
	@Override
	public List<BusinessJoinModel> getBusinessByUser(Long userId) {
		// TODO Auto-generated method stub
		return daoJoin.findByCreatedByAndStatusOrderByUpdatedDateDesc(userId,true);
	}

	@Override
	public BusinessMasterModel createNewBusiness(BusinessMasterModel business) {
		// TODO Auto-generated method stub
		return dao.save(business);
	}

	@Override
	public BusinessJoinModel updateBusiness(Integer wishListId) {
		// TODO Auto-generated method stub
		BusinessJoinModel object = dao.findByIdAndStatus(wishListId,true);
		return daoJoin.save(object);
	}

	@Override
	public BusinessJoinModel deleteBusiness(Integer businessId) {
		// TODO Auto-generated method stub
		BusinessJoinModel object = dao.findByIdAndStatus(businessId,true);
		
		object.setStatus(false);
		return daoJoin.save(object);
	}

	@Override
	public void BusinessFacility(BusinessFacilityModel fac) {
		// TODO Auto-generated method stub
		daoFacility.save(fac);
	}

	@Override
	public void BusinessMedia(BusinessMediaModel media) {
		// TODO Auto-generated method stub
		daoMedia.save(media);
	}
	
	@Override
	public BusinessReportModel createReport(BusinessReportModel object) {
		// TODO Auto-generated method stub
		return daoReport.save(object);
	}
	

	@Override
	public List<BusinessUserWishListModel> getWishlist(Long userMasterId) {
		// TODO Auto-generated method stub
		return daoWishlist.findByCreatedBy(userMasterId);
	}

	public BusinessUserWishListModel wishlist(BusinessUserWishListModel wishlist) {
		// TODO Auto-generated method stub
		BusinessUserWishListModel objectExist = 
				daoWishlist.findFirstByUserMasterIdAndBusinessId(wishlist.getUserMasterId(),wishlist.getBusinessId());
		if(objectExist!=null) {
			daoWishlist.delete(objectExist);
			return wishlist;
		}
		daoWishlist.save(wishlist);
		
		return daoWishlist.findById(wishlist.getId()).get();
	}

	public List<BusinessJoinModel> getSimilarBusiness(Integer businessId, Integer catId) {
		// TODO Auto-generated method stub
		return daoJoin.findTop5ByStatusAndCatIdAndIdNotOrderByUpdatedDateDesc(true,catId,businessId);
	}

	
	public List<BusinessJoinModel> getNearByBusiness(String location) {
		// TODO Auto-generated method stub
		return daoJoin.findByStatusAndAboutBusinessContainingOrderByUpdatedDateDesc(true, location);
	}

	public List<BusinessJoinModel> getBusinessSearch(String key) {
		// TODO Auto-generated method stub
		return daoJoin.findByStatusAndBusinessNameContainingOrAboutBusinessContainingOrderByUpdatedDateDesc(true,key,key);
	}

	
}
