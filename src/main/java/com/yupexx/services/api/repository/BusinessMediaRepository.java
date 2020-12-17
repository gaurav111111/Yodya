package com.yupexx.services.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessMediaModel;

@Repository
public interface BusinessMediaRepository extends JpaRepository<BusinessMediaModel, Integer> {

}