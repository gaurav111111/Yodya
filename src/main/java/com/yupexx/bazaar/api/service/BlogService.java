package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.BlogModel;
import com.yupexx.bazaar.api.repository.BlogRepository;
import com.yupexx.bazaar.api.service.interfaces.BlogInterface;

@Service
public class BlogService implements BlogInterface {

	@Autowired
	BlogRepository dao;
	
	@Override
	public List<BlogModel> getAllBlogs() {
		// TODO Auto-generated method stub
		return dao.findByStatus(true);
	}

	@Override
	public Optional<BlogModel> getBlogById(Integer blogId) {
		// TODO Auto-generated method stub
		return dao.findByIdAndStatus(blogId,true);
	}

	@Override
	public BlogModel addNewBlog(BlogModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public BlogModel updateBlog(Integer blogId,BlogModel object) {
		// TODO Auto-generated method stub
		BlogModel blog = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		blog.setBlogDescription(object.getBlogDescription());
		blog.setBlogName(object.getBlogName());
		return dao.save(blog);
	}
	
	@Override
	public BlogModel deleteBlog(Integer blogId) {
		// TODO Auto-generated method stub
		BlogModel object = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
