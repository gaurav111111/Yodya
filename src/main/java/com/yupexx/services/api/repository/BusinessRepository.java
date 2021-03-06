package com.yupexx.services.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessJoinModel;
import com.yupexx.services.api.model.business.BusinessMasterModel;


@Repository
public interface BusinessRepository extends JpaRepository<BusinessMasterModel, Integer> {

	BusinessJoinModel findByIdAndStatus(Integer businessId, boolean b);
	
	List<BusinessMasterModel> findByStatus(boolean b);

}