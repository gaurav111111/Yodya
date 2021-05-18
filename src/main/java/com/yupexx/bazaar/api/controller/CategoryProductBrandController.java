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

import com.yupexx.bazaar.api.model.CategoryProductBrandModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.repository.CategoryProductBrandRepository;
import com.yupexx.bazaar.api.service.CategoryProductBrandService;

@RestController
@CrossOrigin
public class CategoryProductBrandController {
	
	@Autowired
	CategoryProductBrandService service;
	
	
	@Autowired
	CategoryProductBrandRepository dao;

	@RequestMapping(value = "/bazaar/categoryproductbrands", method = RequestMethod.GET)
	public List<CategoryProductBrandModel> getCategoryProductBrands() {
		System.out.println(this.getClass().getSimpleName() + " - Get all CategoryProductBrands service is invoked.");
		return service.getAllCategoryProductBrands();
	}
	
	
	@RequestMapping(value = "/bazaar/subCategoryproductbrands/{categoryproductbrandId}", method = RequestMethod.GET)
	public List<CategoryProductBrandModel> getSubCategoryProductBrands(@PathVariable Integer categoryproductbrandId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all CategoryProductBrands service is invoked.");
		return service.getAllSubCategoryProductBrands(categoryproductbrandId);
	}
	
	@RequestMapping(value = "/bazaar/categoryproductbrand/{categoryproductbrandId}", method = RequestMethod.GET)
	public Optional<CategoryProductBrandModel> getCategoryProductBrand(@PathVariable Integer categoryproductbrandId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected CategoryProductBrand service is invoked.");
		return service.getCategoryProductBrandById(categoryproductbrandId);
	}
	
	@RequestMapping(value = "/bazaar/categoryproductbrand", method = RequestMethod.POST)
	public CategoryProductBrandModel addCategoryProductBrand(@Valid @RequestBody CategoryProductBrandModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New CategoryProductBrand service is invoked.");
		return service.addNewCategoryProductBrand(object);
	}
	
	@RequestMapping(value = "/bazaar/categoryproductbrand/{categoryproductbrandId}", method = RequestMethod.PUT)
	public CategoryProductBrandModel updateCategoryProductBrand(@Valid @PathVariable Integer categoryproductbrandId,@Valid @RequestBody CategoryProductBrandModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update CategoryProductBrand service is invoked.");
		return service.updateCategoryProductBrand(categoryproductbrandId,object);
	}
	
	@RequestMapping(value = "/bazaar/categoryproductbrand/{categoryproductbrandId}", method = RequestMethod.DELETE)
	public Message deleteCategoryProductBrand(@Valid @PathVariable int categoryproductbrandId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete CategoryProductBrand service is invoked.");
		
		CategoryProductBrandModel object = dao.findById(categoryproductbrandId);
		
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
