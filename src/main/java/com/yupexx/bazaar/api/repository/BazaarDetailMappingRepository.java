package com.yupexx.bazaar.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.yupexx.bazaar.api.model.BazaarDetailMapping;
import com.yupexx.bazaar.api.model.dto.LabelIdDTO;

public interface BazaarDetailMappingRepository extends JpaRepository<BazaarDetailMapping, Integer> {

	List<BazaarDetailMapping> findByAdId(Integer id);

	List<BazaarDetailMapping> findAllLabelIdByAdId(Integer id);

	List<BazaarDetailMapping> findLabelIdByAdId(Integer id);
	
	
	 @Query(value = "SELECT DISTINCT(labelId) as labelID  from bazaar_detail_mapping  WHERE adId = ?1 ", nativeQuery = true)
	  List<LabelIdDTO> findDistinctLabelByAdid( Integer id);
	 
	 @Query(value = "SELECT DISTINCT(categorydetailId) as labelID  from bazaar_detail_mapping  WHERE labelId = ?1 ", nativeQuery = true)
	  List<LabelIdDTO> findDistinctCatByAdid( Integer id);

}
