package com.yupexx.services.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.services.api.model.BusinessCategoryModel;
import com.yupexx.services.api.model.view.BusinessCategoryVwModel;
import com.yupexx.services.api.model.view.BusinessCategoryVwParentModel;
import com.yupexx.services.api.repository.BusinessCategoryRepository;
import com.yupexx.services.api.repository.BusinessCategoryVwParentRepository;
import com.yupexx.services.api.repository.BusinessCategoryVwRepository;
import com.yupexx.services.api.service.interfaces.BusinessCategoryServiceInterface;

@Service
public class BusinessCategoryService implements BusinessCategoryServiceInterface {

	@Autowired
    BusinessCategoryRepository dao;
	@Autowired
	BusinessCategoryVwRepository daoVw;
	
	@Autowired
	BusinessCategoryVwParentRepository daoVwParent;

	@Override
	public List<BusinessCategoryVwModel> getAdCat() {
		// TODO Auto-generated method stub
		return daoVw.findAll();
	}
	
	@Override
	public List<BusinessCategoryVwParentModel> getCat() {
		// TODO Auto-generated method stub
		return daoVwParent.findAll();
	}
	
	@Override
	public List<BusinessCategoryModel> getAllCat() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Optional<BusinessCategoryModel> getCatById(Integer catId) {
		// TODO Auto-generated method stub
		return dao.findById(catId);
	}
	
	@Override
	public Optional<BusinessCategoryVwParentModel> getCatNestedById(Integer catId) {
		// TODO Auto-generated method stub
		return daoVwParent.findById(catId);
	}

	@Override
	public BusinessCategoryModel addCat(BusinessCategoryModel cat) {
		// TODO Auto-generated method stub
		return (BusinessCategoryModel) dao.save(cat);
	}

	@Override
	public BusinessCategoryModel updateCat(BusinessCategoryModel cat) {
		// TODO Auto-generated method stub
		return (BusinessCategoryModel) dao.save(cat);
	}

	@Override
	public void deleteCat(Integer catId) {
		// TODO Auto-generated method stub
		dao.deleteById(catId);
	}

}
