package com.yupexx.bazaar.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CategoryAccessoryModel;
import com.yupexx.bazaar.api.model.CategoryProductBrandModel;
import com.yupexx.bazaar.api.repository.CategoryProductBrandRepository;
import com.yupexx.bazaar.api.service.interfaces.CategoryProductBrandInterface;

@Service
public class CategoryProductBrandService implements CategoryProductBrandInterface {

	@Autowired
	CategoryProductBrandRepository dao;
	
	@Override
	public List<CategoryProductBrandModel> getAllCategoryProductBrands() {
		// TODO Auto-generated method stub
	//	return dao.findByStatus(true);
		return dao.findAll();
	}

	@Override
	public Optional<CategoryProductBrandModel> getCategoryProductBrandById(Integer blogId) {
		// TODO Auto-generated method stub
		//return dao.findByIdAndStatus(blogId,true);
		return dao.findById(blogId);
	}

	@Override
	public CategoryProductBrandModel addNewCategoryProductBrand(CategoryProductBrandModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public CategoryProductBrandModel updateCategoryProductBrand(Integer blogId,CategoryProductBrandModel object) {
		// TODO Auto-generated method stub
		CategoryProductBrandModel blog = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		blog.setBrandName(object.getBrandName());
		return dao.save(blog);
	}
	
	@Override
	public CategoryProductBrandModel deleteCategoryProductBrand(Integer blogId) {
		// TODO Auto-generated method stub
		CategoryProductBrandModel object = dao.findById(blogId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

	public List<CategoryProductBrandModel> getAllSubCategoryProductBrands(Integer categoryproductbrandId) {
		// TODO Auto-generated method stub
		//List<CategoryProductBrandModel> catP = dao.findByStatus(true);
		List<CategoryProductBrandModel> catP = dao.findAll();
		List<CategoryProductBrandModel> data = new ArrayList();
		if(catP.size()>0) {
			for(CategoryProductBrandModel ca:catP) {
				if(categoryproductbrandId==ca.getCatId()) {
					data.add(ca);
				}
			}
		}
		
		
		return data;
	}

}
