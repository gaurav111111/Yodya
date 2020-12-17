package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostTagModel;

@Repository
public interface AdPostTagRepository extends JpaRepository<AdPostTagModel, Integer> {

}