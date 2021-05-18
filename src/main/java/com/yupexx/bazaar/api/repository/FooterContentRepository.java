package com.yupexx.bazaar.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yupexx.bazaar.api.model.FooterContentModel;

@Repository
public interface FooterContentRepository extends JpaRepository<FooterContentModel, Integer>{

	List<FooterContentModel> findAll();
	List<FooterContentModel> findByStatus(Boolean Status);
	FooterContentModel findBySlugs(String Slug);
	FooterContentModel findById(int id);
	FooterContentModel findBySlugsAndStatus(String Slug,Boolean Status);
	FooterContentModel findByIdAndStatus(int id,Boolean Status);
}
