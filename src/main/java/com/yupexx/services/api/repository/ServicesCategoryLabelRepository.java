package com.yupexx.services.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.ServicesCategoryLabel;

@Repository
public interface ServicesCategoryLabelRepository extends JpaRepository<ServicesCategoryLabel, Integer> {
	
	List<ServicesCategoryLabel> findByCategoryId(int id);

}
