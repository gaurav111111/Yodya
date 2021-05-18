package com.yupexx.services.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yupexx.services.api.model.ServicesCategoryDetails;

@Repository
public interface ServiceCategoryDetailRepository extends JpaRepository<ServicesCategoryDetails, Integer> {

	
	List<ServicesCategoryDetails> findByLabelId(int id);

 ServicesCategoryDetails findByCategoryId(Integer categorydetailId);
	
	
}
