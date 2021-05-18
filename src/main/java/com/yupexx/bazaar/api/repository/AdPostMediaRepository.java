package com.yupexx.bazaar.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostMediaModel;

@Repository
public interface AdPostMediaRepository extends JpaRepository<AdPostMediaModel, Integer> {

	List<AdPostMediaModel> findByAdId(Integer id);

}