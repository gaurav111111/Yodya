package com.yupexx.services.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.business.BusinessFacilityModel;
import com.yupexx.services.api.model.business.BusinessJoinModel;
import com.yupexx.services.api.model.business.BusinessMasterModel;
import com.yupexx.services.api.model.business.BusinessMediaModel;
import com.yupexx.services.api.model.business.BusinessReportModel;
import com.yupexx.services.api.model.business.BusinessUserWishListModel;
import com.yupexx.services.api.model.dto.BusinessMasterDTO;

public interface BusinessInterface {

	List<BusinessJoinModel> getAllBusiness();
	
	List<BusinessJoinModel> getSponsoredBusiness();
	
	List<BusinessJoinModel> getAllBusinessFilter(List<Integer> catId);
	
	BusinessMasterDTO getBusinessById(Integer businessId);
	
	BusinessMasterModel createNewBusiness(BusinessMasterModel business);
	
	BusinessJoinModel updateBusiness(Integer businessId);
	
	BusinessJoinModel deleteBusiness(Integer businessId);

	List<BusinessJoinModel> getBusinessByUser(Long userId);

	void BusinessFacility(BusinessFacilityModel fac);

	void BusinessMedia(BusinessMediaModel media);
	
	BusinessReportModel createReport(BusinessReportModel object);

	List<BusinessUserWishListModel> getWishlist(Long userMasterId);

	
}
