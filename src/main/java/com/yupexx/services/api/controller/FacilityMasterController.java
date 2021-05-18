package com.yupexx.services.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.services.api.model.FacilityMasterModel;
import com.yupexx.services.api.service.FacilityService;

@RestController
@CrossOrigin
public class FacilityMasterController {
	
	@Autowired
	FacilityService service;
	
	@RequestMapping(value = "/services/facility", method = RequestMethod.GET)
	public List<FacilityMasterModel> getAllFacility(){
		return service.getAllFacility();
	}
	
	
	@RequestMapping(value = "/services/facility/{id}", method = RequestMethod.GET)
	public Optional<FacilityMasterModel> getAllFacility(@PathVariable Integer facilityId){
		
		return service.getFacilityById(facilityId);
	}

}
