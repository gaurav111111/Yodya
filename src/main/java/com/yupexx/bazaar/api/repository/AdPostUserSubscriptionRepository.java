package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostUserSubscriptionsModel;

@Repository
public interface AdPostUserSubscriptionRepository extends JpaRepository<AdPostUserSubscriptionsModel, Integer> {

}