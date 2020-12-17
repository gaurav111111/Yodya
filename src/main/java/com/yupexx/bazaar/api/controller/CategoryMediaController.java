package com.yupexx.bazaar.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.CategoryMediaModel;
import com.yupexx.bazaar.api.service.CategoryMediaService;

@RestController
public class CategoryMediaController {
	
	@Autowired
	CategoryMediaService service;

	@RequestMapping(value = "/bazaar/catmedias", method = RequestMethod.GET)
	public List<CategoryMediaModel> getCategoryMedias() {
		System.out.println(this.getClass().getSimpleName() + " - Get all CategoryMedias service is invoked.");
		return service.getAllCategoryMedias();
	}
	
	@RequestMapping(value = "/bazaar/catmedia/{catmediaId}", method = RequestMethod.GET)
	public Optional<CategoryMediaModel> getCategoryMedia(@PathVariable Integer catmediaId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected CategoryMedia service is invoked.");
		return service.getCategoryMediaById(catmediaId);
	}
	
	@RequestMapping(value = "/bazaar/catmedia", method = RequestMethod.POST)
	public CategoryMediaModel addCategoryMedia(@Valid @RequestBody CategoryMediaModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New CategoryMedia service is invoked.");
		return service.addNewCategoryMedia(object);
	}
	
	@RequestMapping(value = "/bazaar/catmedia/{catmediaId}", method = RequestMethod.PUT)
	public CategoryMediaModel updateCategoryMedia(@Valid @PathVariable Integer catmediaId,@Valid @RequestBody CategoryMediaModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update CategoryMedia service is invoked.");
		return service.updateCategoryMedia(catmediaId,object);
	}
	
	@RequestMapping(value = "/bazaar/catmedia/{catmediaId}", method = RequestMethod.DELETE)
	public CategoryMediaModel deleteCategoryMedia(@Valid @PathVariable Integer catmediaId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete CategoryMedia service is invoked.");
		return service.deleteCategoryMedia(catmediaId);
	}
	
}
