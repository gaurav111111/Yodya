package com.yupexx.services.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.yupexx.services.api.model.business.BusinessJoinModel;


@Repository
public interface BusinessJoinRepository extends JpaRepository<BusinessJoinModel, Integer> {

	Optional<BusinessJoinModel> findByIdAndStatusOrderByUpdatedDateDesc(Integer businessId, boolean b);
	
	List<BusinessJoinModel> findByCreatedByAndStatus(Long userId,boolean b);
	
	List<BusinessJoinModel> findByStatus(boolean b);
	
	List<BusinessJoinModel> findTop5ByStatusAndCatIdAndIdNotOrderByUpdatedDateDesc(boolean b,Integer catId,Integer id);
	
	List<BusinessJoinModel> findByStatusAndAboutBusinessContainingOrderByUpdatedDateDesc(boolean b,String aboutBusiness);
	
	List<BusinessJoinModel> findByStatusAndBusinessNameContainingOrAboutBusinessContainingOrderByUpdatedDateDesc(boolean b,String t,String d);
	
	List<BusinessJoinModel> findByCreatedByAndStatusOrderByUpdatedDateDesc(Long createdBy,Boolean b);

	List<BusinessJoinModel> findTop5ByStatusOrderByUpdatedDateDesc(boolean b);
	
	//List<BusinessJoinModel> findTop12ByAdPriceGreaterThanAndStatusOrderByUpdatedDateDesc(float price,boolean b);

	List<BusinessJoinModel> findByStatusAndCatIdOrderByUpdatedDateDesc(boolean b, Integer catId);
	
	@Query("SELECT e FROM BusinessJoinModel e WHERE e.catId IN (:catId) and e.status=1")
	List<BusinessJoinModel> findByCatIds(List<Integer> catId);
	

}