package com.yupexx.bazaar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.view.SellerAdCountVwModel;

@Repository
public interface SellerAdCountVwRepository extends JpaRepository<SellerAdCountVwModel, Long>{

}
