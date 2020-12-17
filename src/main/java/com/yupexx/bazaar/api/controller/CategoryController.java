package com.yupexx.bazaar.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.bazaar.api.model.view.CategoryVwModel;
import com.yupexx.bazaar.api.service.interfaces.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService service;

	@RequestMapping(value = "/bazaar/category", method = RequestMethod.GET)
	public List<CategoryVwModel> getcategory() {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		return service.getCat();
	}
	
	@RequestMapping(value = "/bazaar/category/all", method = RequestMethod.GET)
	public List<CategoryModel> getAllCategory() {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		return service.getAllCat();
	}

	@RequestMapping(value = "/bazaar/category/{categoryId}", method = RequestMethod.GET)
	public CategoryVwModel getcategoryById(@PathVariable Integer categoryId) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get category details by id is invoked.");

		Optional<CategoryVwModel> category = service.getCatNestedById(categoryId);
		if (!category.isPresent())
			throw new Exception("Could not find category with id- " + categoryId);

		return category.get();
	}

	@RequestMapping(value = "/bazaar/category", method = RequestMethod.POST)
	public CategoryModel createcategory(@RequestBody CategoryModel category) {
		System.out.println(this.getClass().getSimpleName() + " - Create new category method is invoked.");
		return service.addCat(category);
	}

	@RequestMapping(value = "/bazaar/category/{categoryId}", method = RequestMethod.PUT)
	public CategoryModel updatecategory(@RequestBody CategoryModel updcategory, @PathVariable Integer categoryId) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update category details by id is invoked.");

		Optional<CategoryModel> category = service.getCatById(categoryId);
		if (!category.isPresent())
			throw new Exception("Could not find category with id- " + categoryId);


		// Required for the "where" clause in the sql query template.
		updcategory.setId(categoryId);
		return service.updateCat(updcategory);
	}

	@RequestMapping(value = "/bazaar/category/{categoryId}", method = RequestMethod.DELETE)
	public void deletecategoryById(@PathVariable Integer categoryId) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete category by id is invoked.");

		Optional<CategoryModel> emp = service.getCatById(categoryId);
		if (!emp.isPresent())
			throw new Exception("Could not find category with id- " + categoryId);

		service.deleteCat(categoryId);
	}

}
