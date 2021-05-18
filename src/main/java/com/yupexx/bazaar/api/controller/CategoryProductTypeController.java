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

import com.yupexx.bazaar.api.model.CategoryProductTypeModel;
import com.yupexx.bazaar.api.model.FooterContentModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.repository.CategoryProductTypeRepository;
import com.yupexx.bazaar.api.service.CategoryProductTypeService;

@RestController
@CrossOrigin
public class CategoryProductTypeController {
	
	@Autowired
	CategoryProductTypeService service;
	
	@Autowired
	CategoryProductTypeRepository dao;

	@RequestMapping(value = "/bazaar/categoryproducttypes", method = RequestMethod.GET)
	public List<CategoryProductTypeModel> getCategoryProductTypes() {
		System.out.println(this.getClass().getSimpleName() + " - Get all CategoryProductTypes service is invoked.");
		return service.getAllCategoryProductTypes();
	}
	
	@RequestMapping(value = "/bazaar/categoryproducttype/{categoryproducttypeId}", method = RequestMethod.GET)
	public Optional<CategoryProductTypeModel> getCategoryProductType(@PathVariable Integer categoryproducttypeId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected CategoryProductType service is invoked.");
		return service.getCategoryProductTypeById(categoryproducttypeId);
	}
	
	@RequestMapping(value = "/bazaar/categoryproducttype", method = RequestMethod.POST)
	public CategoryProductTypeModel addCategoryProductType(@Valid @RequestBody CategoryProductTypeModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New CategoryProductType service is invoked.");
		return service.addNewCategoryProductType(object);
	}
	
	@RequestMapping(value = "/bazaar/categoryproducttype/{categoryproducttypeId}", method = RequestMethod.PUT)
	public CategoryProductTypeModel updateCategoryProductType(@Valid @PathVariable Integer categoryproducttypeId,@Valid @RequestBody CategoryProductTypeModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update CategoryProductType service is invoked.");
		return service.updateCategoryProductType(categoryproducttypeId,object);
	}
	
	@RequestMapping(value = "/bazaar/categoryproducttype/{categoryproducttypeId}", method = RequestMethod.DELETE)
	public Message deleteCategoryProductType(@Valid @PathVariable int id) {
		
		System.out.println(this.getClass().getSimpleName() + " - Delete CategoryProductType service is invoked.");
		Message msg=new Message();
		CategoryProductTypeModel object = dao.findById(id);
		if(object!=null) {
			if(object.getStatus()) {
				object.setStatus(false);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Product Type Inactive Successfully");
				return msg;
			}else {
				object.setStatus(true);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Product Type Active Successfully");
				return msg;
			}
		}else {
			msg.setCode(400);
			msg.setMessage("Product Type not Found");
			return msg;
		}
		
		
		
	}
	
}
