package com.yupexx.bazaar.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.BlogModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.repository.BlogRepository;
import com.yupexx.bazaar.api.service.BlogService;

@RestController
@CrossOrigin
public class BlogController {
	
	@Autowired
	BlogService service;
	
	@Autowired
	BlogRepository dao;

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
	public Message deleteBlog(@Valid @PathVariable Integer blogId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete Blog service is invoked.");
		
		Message msg = new Message();
		BlogModel object = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		
		if(object.getStatus()) {
			object.setStatus(false);
			dao.save(object);
			msg.setCode(200);
			msg.setMessage("Blog InActive Sucessfully");
			return msg;
		}else {
			object.setStatus(true);
			dao.save(object);
			msg.setCode(200);
			msg.setMessage("Blog Active Sucessfully");
			return msg;
		}
		
	
		
		
	}
	
}
