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

import com.yupexx.bazaar.api.model.CategoryTagsModel;
import com.yupexx.bazaar.api.service.CategoryTagsService;

@RestController
public class CategoryTagsController {
	
	@Autowired
	CategoryTagsService service;

	@RequestMapping(value = "/bazaar/categorytags", method = RequestMethod.GET)
	public List<CategoryTagsModel> getCategoryTagss() {
		System.out.println(this.getClass().getSimpleName() + " - Get all CategoryTagss service is invoked.");
		return service.getAllCategoryTagss();
	}
	
	@RequestMapping(value = "/bazaar/categorytag/{categorytagId}", method = RequestMethod.GET)
	public Optional<CategoryTagsModel> getCategoryTags(@PathVariable Integer categorytagId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected CategoryTags service is invoked.");
		return service.getCategoryTagsById(categorytagId);
	}
	
	@RequestMapping(value = "/bazaar/categorytag", method = RequestMethod.POST)
	public CategoryTagsModel addCategoryTags(@Valid @RequestBody CategoryTagsModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New CategoryTags service is invoked.");
		return service.addNewCategoryTags(object);
	}
	
	@RequestMapping(value = "/bazaar/categorytag/{categorytagId}", method = RequestMethod.PUT)
	public CategoryTagsModel updateCategoryTags(@Valid @PathVariable Integer categorytagId,@Valid @RequestBody CategoryTagsModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update CategoryTags service is invoked.");
		return service.updateCategoryTags(categorytagId,object);
	}
	
	@RequestMapping(value = "/bazaar/categorytag/{categorytagId}", method = RequestMethod.DELETE)
	public CategoryTagsModel deleteCategoryTags(@Valid @PathVariable Integer categorytagId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete CategoryTags service is invoked.");
		return service.deleteCategoryTags(categorytagId);
	}
	
}
