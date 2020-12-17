package com.yupexx.services.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessFacilityModel;

@Repository
public interface BusinessFacilityRepository extends JpaRepository<BusinessFacilityModel, Integer> {

}