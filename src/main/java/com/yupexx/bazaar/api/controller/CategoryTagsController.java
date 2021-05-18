package com.yupexx.bazaar.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.CategoryTagsModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.repository.CategoryTagsRepository;
import com.yupexx.bazaar.api.service.CategoryTagsService;

@RestController
@CrossOrigin
public class CategoryTagsController {
	
	@Autowired
	CategoryTagsService service;
	
	@Autowired
	CategoryTagsRepository dao;

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
	public Message deleteCategoryTags(@Valid @PathVariable int categorytagId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete CategoryTags service is invoked.");
		CategoryTagsModel object = dao.findById(categorytagId);
		
		Message msg=new Message();
		if(object!=null) {
			if(object.getStatus()) {
				object.setStatus(false);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Accessory Inactive Successfully");
				return msg;
			}else {
				object.setStatus(true);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Accessory Active Successfully");
				return msg;
			}
		}else {
			msg.setCode(400);
			msg.setMessage("Accessory not Found");
			return msg;
		}
		
		
		
		
	}
	
}
