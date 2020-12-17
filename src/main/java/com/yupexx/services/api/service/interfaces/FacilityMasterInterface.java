package com.yupexx.services.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.services.api.model.FacilityMasterModel;

public interface FacilityMasterInterface {

	List<FacilityMasterModel> getAllFacility();
	
	Optional<FacilityMasterModel> getFacilityById(Integer id);
	
	FacilityMasterModel createFacility(FacilityMasterModel object);
	
	FacilityMasterModel updateFacility(Integer id, FacilityMasterModel object);
	
	FacilityMasterModel deleteFacility(Integer id);
}
