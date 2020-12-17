package com.yupexx.bazaar.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostUserJoinModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserModel;

@Repository
public interface AdPostedRepository extends JpaRepository<AdPostUserModel, Integer> {

	AdPostUserModel findOneByIdAndStatus(Integer adId,Boolean status);
}
