package com.yupexx.bazaar.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.CategoryAccessoryModel;
import com.yupexx.bazaar.api.service.CategoryAccessoryService;

@RestController
public class CategoryAccessoryController {
	
	@Autowired
	CategoryAccessoryService service;

	@RequestMapping(value = "/bazaar/categoryaccessorys", method = RequestMethod.GET)
	public List<CategoryAccessoryModel> getCategoryAccessorys() {
		System.out.println(this.getClass().getSimpleName() + " - Get all CategoryAccessorys service is invoked.");
		return service.getAllCategoryAccessorys();
	}
	
	@RequestMapping(value = "/bazaar/categoryaccessory/{categoryaccessoryId}", method = RequestMethod.GET)
	public Optional<CategoryAccessoryModel> getCategoryAccessory(@PathVariable Integer categoryaccessoryId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected CategoryAccessory service is invoked.");
		return service.getCategoryAccessoryById(categoryaccessoryId);
	}
	
	@RequestMapping(value = "/bazaar/categoryaccessory", method = RequestMethod.POST)
	public CategoryAccessoryModel addCategoryAccessory(@Valid @RequestBody CategoryAccessoryModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New CategoryAccessory service is invoked.");
		return service.addNewCategoryAccessory(object);
	}
	
	@RequestMapping(value = "/bazaar/categoryaccessory/{categoryaccessoryId}", method = RequestMethod.PUT)
	public CategoryAccessoryModel updateCategoryAccessory(@Valid @PathVariable Integer categoryaccessoryId,@Valid @RequestBody CategoryAccessoryModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update CategoryAccessory service is invoked.");
		return service.updateCategoryAccessory(categoryaccessoryId,object);
	}
	
	@RequestMapping(value = "/bazaar/categoryaccessory/{categoryaccessoryId}", method = RequestMethod.DELETE)
	public CategoryAccessoryModel deleteCategoryAccessory(@Valid @PathVariable Integer categoryaccessoryId,Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Delete CategoryAccessory service is invoked.");
		System.out.println(this.getClass().getSimpleName()+""+authentication.getDetails());
		return service.deleteCategoryAccessory(categoryaccessoryId);
	}
	
}
