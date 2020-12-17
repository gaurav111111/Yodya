package com.yupexx.services.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.services.api.model.FacilityMasterModel;
import com.yupexx.services.api.repository.FacilityRepository;
import com.yupexx.services.api.service.interfaces.FacilityMasterInterface;

@Service
public class FacilityService implements FacilityMasterInterface {

	@Autowired
	FacilityRepository dao;
	
	@Override
	public List<FacilityMasterModel> getAllFacility() {
		// TODO Auto-generated method stub
		return dao.findByStatus(true);
	}

	@Override
	public Optional<FacilityMasterModel> getFacilityById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByIdAndStatus(id, true);
	}

	@Override
	public FacilityMasterModel createFacility(FacilityMasterModel object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacilityMasterModel updateFacility(Integer id, FacilityMasterModel object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacilityMasterModel deleteFacility(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
