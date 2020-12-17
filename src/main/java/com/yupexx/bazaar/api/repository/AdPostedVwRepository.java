package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ads.AdPostUserJoinModel;
import com.yupexx.bazaar.api.model.ads.AdPostUserModel;

@Repository
public interface AdPostedVwRepository extends JpaRepository<AdPostUserJoinModel, Integer> {


	List<AdPostUserJoinModel> findByStatusAndCatIdAndIdNotOrderByUpdatedDateDesc(boolean b,Integer catId,Integer id);
	
	List<AdPostUserJoinModel> findByStatusAndLocationContainingOrderByUpdatedDateDesc(boolean b,String location);
	
	List<AdPostUserJoinModel> findByStatusAndAdTitleContainingOrAdDescriptionContainingOrderByUpdatedDateDesc(boolean b,String t,String d);
	
	List<AdPostUserJoinModel> findByCreatedByAndStatusOrderByUpdatedDateDesc(Long createdBy,Boolean b);

	List<AdPostUserJoinModel> findTop12ByStatusOrderByUpdatedDateDesc(boolean b);
	
	List<AdPostUserJoinModel> findTop12ByAdPriceGreaterThanAndStatusOrderByUpdatedDateDesc(float price,boolean b);

	List<AdPostUserJoinModel> findByStatusAndCatIdOrderByUpdatedDateDesc(boolean b, Integer catId);
	
	@Query("SELECT e FROM AdPostUserJoinModel e WHERE e.catId IN (:catId) and e.status=1")
	List<AdPostUserJoinModel> findByCatIds(List<Integer> catId);

}
