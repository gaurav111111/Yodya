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

import com.yupexx.bazaar.api.model.BlogModel;
import com.yupexx.bazaar.api.service.BlogService;

@RestController
public class BlogController {
	
	@Autowired
	BlogService service;

	@RequestMapping(value = "/bazaar/blogs", method = RequestMethod.GET)
	public List<BlogModel> getBlogs() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Blogs service is invoked.");
		return service.getAllBlogs();
	}
	
	@RequestMapping(value = "/bazaar/blog/{blogId}", method = RequestMethod.GET)
	public Optional<BlogModel> getBlog(@PathVariable Integer blogId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected Blog service is invoked.");
		return service.getBlogById(blogId);
	}
	
	@RequestMapping(value = "/bazaar/blog", method = RequestMethod.POST)
	public BlogModel addBlog(@Valid @RequestBody BlogModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New Blog service is invoked.");
		return service.addNewBlog(object);
	}
	
	@RequestMapping(value = "/bazaar/blog/{blogId}", method = RequestMethod.PUT)
	public BlogModel updateBlog(@Valid @PathVariable Integer blogId,@Valid @RequestBody BlogModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update Blog service is invoked.");
		return service.updateBlog(blogId,object);
	}
	
	@RequestMapping(value = "/bazaar/blog/{blogId}", method = RequestMethod.DELETE)
	public BlogModel deleteBlog(@Valid @PathVariable Integer blogId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete Blog service is invoked.");
		return service.deleteBlog(blogId);
	}
	
}
