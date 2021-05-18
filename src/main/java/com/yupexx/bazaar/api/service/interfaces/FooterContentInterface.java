package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.FooterContentModel;

public interface FooterContentInterface {

	List<FooterContentModel> getAllFooterContent();
	FooterContentModel getFooterContentById(int id);
	FooterContentModel addNewFooterContent(FooterContentModel object);
	FooterContentModel updateFooterContentById(int id, FooterContentModel object);
	FooterContentModel deleteFooterContentBySlug(String slug);
	FooterContentModel deleteFooterContentById(int id);
	FooterContentModel getFooterContentBySlug(String slug);
	FooterContentModel updateFooterContentBySlug(String slug, FooterContentModel object);
	
	
	
	
	
	
	
}
