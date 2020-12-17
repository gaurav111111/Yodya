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

import com.yupexx.bazaar.api.model.CategoryProductBrandModel;
import com.yupexx.bazaar.api.service.CategoryProductBrandService;

@RestController
public class CategoryProductBrandController {
	
	@Autowired
	CategoryProductBrandService service;

	@RequestMapping(value = "/bazaar/categoryproductbrands", method = RequestMethod.GET)
	public List<CategoryProductBrandModel> getCategoryProductBrands() {
		System.out.println(this.getClass().getSimpleName() + " - Get all CategoryProductBrands service is invoked.");
		return service.getAllCategoryProductBrands();
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
	public CategoryProductBrandModel deleteCategoryProductBrand(@Valid @PathVariable Integer categoryproductbrandId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete CategoryProductBrand service is invoked.");
		return service.deleteCategoryProductBrand(categoryproductbrandId);
	}
	
}
