package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.CronModel;

@Repository
public interface CronRepository extends JpaRepository<CronModel, Integer>{

	Optional<CronModel> findByIdAndStatus(Integer id, boolean b);

	List<CronModel> findByStatusAndIsProcessed(boolean b,Integer a);

	List<CronModel> findByStatusAndCreatedByOrderByIdDesc(boolean b, Long userId);

	List<CronModel> findByCreatedByOrderByIdDesc(Long userId);
	
}