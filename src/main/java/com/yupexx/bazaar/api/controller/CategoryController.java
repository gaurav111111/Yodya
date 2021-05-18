package com.yupexx.bazaar.api.controller;

import java.util.ArrayList;
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
import com.yupexx.bazaar.api.model.CategoryModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.model.view.CategoryVwModel;
import com.yupexx.bazaar.api.service.AdPostedService;
import com.yupexx.bazaar.api.service.UserService;
import com.yupexx.bazaar.api.service.interfaces.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@Autowired
	UserIdentity identity;
	
	@Autowired
	AdPostedService postService;
	
	@Autowired
	UserService userService;

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
	public Message createcategory(@RequestBody CategoryModel category) {
		Message message = new Message();
		System.out.println(this.getClass().getSimpleName() + " - Create new category method is invoked.");
		List<CategoryModel> category1= service.findCategory(category.getCategoryName());
		System.out.println("check category-------------------"+category1.toString());
		if(!(category1.isEmpty()) || category1.size()>0) {
			message.setCode(400);
			message.setContent("Category Already Present!");
			return message;	
		}else {
			service.addCat(category);
			message.setCode(200);
			message.setContent("Category Saved Sucessfully!");
			return message;	
		}
	}

	@RequestMapping(value = "/bazaar/category/{categoryId}", method = RequestMethod.PUT)
	public CategoryModel updatecategory(@RequestBody CategoryModel updcategory, @PathVariable Integer categoryId,Authentication authentication) throws Exception {
		
		System.out.println(this.getClass().getSimpleName() + " - Update category details by id is invoked.");
		
		Optional<CategoryModel> optionalCategory = service.getCatById(categoryId);
		
		if (!optionalCategory.isPresent()) {
			throw new Exception("Could not find category with id- " + categoryId);
		}
		System.out.println("Request Category Model"+updcategory);
		
		CategoryModel category=optionalCategory.get();
		UserModel user = identity.get(authentication);
		
		System.out.println("Category Model"+category);
		if(updcategory.getCategoryName()!=null) {
			category.setCategoryName(updcategory.getCategoryName());
		}
		category.setUpdatedBy(user.getId());

		// Required for the "where" clause in the sql query template.
		category.setId(categoryId);
		return service.updateCat(category);
	}


	@RequestMapping(value = "/bazaar/category/{categoryId}", method = RequestMethod.DELETE)
	public Message deletecategoryById(@PathVariable Integer categoryId,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete category by id is invoked.");
		Message msg=new Message();
		if(!userService.userRoleCheck(authentication).equalsIgnoreCase("admin")) {
			//throw new Exception("Only Admin can delete category.");
			    msg.setCode(400);
		        msg.setMessage("Only Admin can delete category.");
		        return msg;
			
		}else {
			Optional<CategoryModel> emp = service.getCatById(categoryId);
			if (!emp.isPresent()) {
				//throw new Exception("Could not find category with id- " + categoryId);
				    msg.setCode(400);
			        msg.setMessage("Could not find category with id."+categoryId);
			        return msg;
			}else {
				CategoryModel category=emp.get();
				if(category!=null) {
					if(category.getStatus()==false) {
						 category.setStatus(true);
						 service.updateCat(category);
					        msg.setCode(200);
					        msg.setMessage("Category has been Active successfully.");
					        return msg;
					}else {
					    if(!postService.getAdByCatId(categoryId).isEmpty()) {	
					    	  // 	throw new Exception("This category can not be deleted.");
					    		    msg.setCode(400);
							        msg.setMessage("This category can not be deleted.");
							        return msg;
					    	}
					          else {
						    		List<CategoryModel> cm=service.getAllCat();	
						    		if(checkCatWithPost(cm,categoryId)) {
						    		//	throw new Exception("This category can not be deleted.");
						    			    msg.setCode(400);
									        msg.setMessage("This category can not be deleted.");
									        return msg;
						    		}
					    	}
					    	
						        category.setStatus(false);
						        service.updateCat(category);
						        msg.setCode(200);
						        msg.setMessage("Category has been inactive successfully.");
						        return msg;	
					}
				}
				
			}
			    msg.setCode(400);
		        msg.setMessage("Error");
		        return msg;		
		}
		 
	}

	public boolean checkPost(Integer c) {
		
		if(postService.getAdByCatId(c).isEmpty()) {
    		return false;
    		}
		
		return true;
		}
	
	public boolean checkCatWithPost(List<CategoryModel> cm,Integer catId) {
		
		List<CategoryModel> catm=new ArrayList<>();
		
		System.out.println("inside checkCatWithPost");
		for(CategoryModel c:cm) {
			
			if((c.getParentCatId()!=null && c.getParentCatId()!=0) && c.getParentCatId()==catId ) {
				
				if(checkPost(c.getId())) {
					return true;
				}	
				else {
					catm.add(c);	
				}
			}
		}
		
		if(catm.size()>0) {
			
			for(CategoryModel cat:catm) {
				
				for(CategoryModel c:cm) {
				
					if((c.getParentCatId()!=null && c.getParentCatId()!=0) && c.getParentCatId()==cat.getId() ) {
						
						if(checkPost(c.getId())) {
							return true;
						}	
						
					}					
				}
			}
			
		}
	
		return false;
	}
	
	
	

}
