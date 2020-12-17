package com.yupexx.services.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.business.BusinessAnalyticsModel;

@Repository
public interface BusinessAnalyticsRepository extends JpaRepository<BusinessAnalyticsModel, Integer> {

}