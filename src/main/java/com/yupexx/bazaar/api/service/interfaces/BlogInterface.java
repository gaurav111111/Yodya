package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.BlogModel;

public interface BlogInterface {

	List<BlogModel> getAllBlogs();
	Optional getBlogById(Integer blogId);
	BlogModel addNewBlog(BlogModel object);
	BlogModel updateBlog(Integer blogId,BlogModel object);
	BlogModel deleteBlog(Integer blogId);
}
