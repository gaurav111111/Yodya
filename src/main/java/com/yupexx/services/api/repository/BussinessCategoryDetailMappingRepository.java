package com.yupexx.services.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yupexx.services.api.model.BusinessDetailMapping;


public interface BussinessCategoryDetailMappingRepository extends JpaRepository<BusinessDetailMapping, Integer> {

	
	List<BusinessDetailMapping> findByBusinessId(int id);

}
