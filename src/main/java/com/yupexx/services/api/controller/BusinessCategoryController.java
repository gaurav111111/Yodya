package com.yupexx.services.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.BusinessCategoryModel;
import com.yupexx.services.api.model.view.BusinessCategoryVwParentModel;
import com.yupexx.services.api.service.BusinessCategoryService;

@RestController
@CrossOrigin
public class BusinessCategoryController {

	@Autowired
	BusinessCategoryService service;
	
	@Autowired
	UserIdentity identity;

	@RequestMapping(value = "/services/category", method = RequestMethod.GET)
	public Message getcategory() {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		return service.getCat();
	}
	
	@RequestMapping(value = "/services/category/all", method = RequestMethod.GET)
	public List<BusinessCategoryModel> getAllCategory() {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		return service.getAllCat();
	}

	@RequestMapping(value = "/services/category/{categoryId}", method = RequestMethod.GET)
	public BusinessCategoryVwParentModel getcategoryById(@PathVariable Integer categoryId) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get category details by id is invoked.");
		Optional<BusinessCategoryVwParentModel> category = service.getCatNestedById(categoryId);
		if (!category.isPresent())
			throw new Exception("Could not find category with id- " + categoryId);
		return category.get();
	}

	@RequestMapping(value = "/services/category", method = RequestMethod.POST)
	public BusinessCategoryModel createcategory(@RequestBody BusinessCategoryModel category) {
		System.out.println(this.getClass().getSimpleName() + " - Create new category method is invoked.");
		return service.addCat(category);
	}

	@RequestMapping(value = "/services/category/{categoryId}", method = RequestMethod.PUT)
	public BusinessCategoryModel updatecategory(@RequestBody BusinessCategoryModel updcategory, @PathVariable Integer categoryId, Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update category details by id is invoked.");

		Optional<BusinessCategoryModel> category = service.getCatById(categoryId);
		if (!category.isPresent())
			throw new Exception("Could not find category with id- " + categoryId);

		if(authentication==null) {
			throw new Exception("Please Provide JWT Token");
		}
		UserModel user = identity.get(authentication);
		if(user==null) {
			throw new Exception("Please Provide Valid Token");
		}
		
        if(updcategory.getCategoryName()!=null) {
        	category.get().setCategoryName(updcategory.getCategoryName());
		}
        
        category.get().setUpdatedBy(user.getId());
		// Required for the "where" clause in the sql query template.
        category.get().setId(categoryId);
		
		return service.updateCat(category.get());
	}

	@RequestMapping(value = "/services/category/{categoryId}", method = RequestMethod.DELETE)
	public void deletecategoryById(@PathVariable Integer categoryId) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete category by id is invoked.");

		Optional<BusinessCategoryModel> emp = service.getCatById(categoryId);
		if (!emp.isPresent())
			throw new Exception("Could not find category with id- " + categoryId);
		
		if(emp.get().getStatus()) {
			emp.get().setStatus(false);
			service.updateCat(emp.get());
		}else {
			emp.get().setStatus(true);
			service.updateCat(emp.get());
		}
	}

}
