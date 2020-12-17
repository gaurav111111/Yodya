package com.yupexx.services.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.FacilityMasterModel;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityMasterModel, Integer> {
	
	List<FacilityMasterModel> findByStatus(boolean status);
	
	Optional<FacilityMasterModel> findByIdAndStatus(Integer id,boolean status);

}