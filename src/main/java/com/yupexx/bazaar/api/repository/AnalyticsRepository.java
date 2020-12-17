package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostAnalyticsModel;

@Repository
public interface AnalyticsRepository extends JpaRepository<AdPostAnalyticsModel, Integer> {

}