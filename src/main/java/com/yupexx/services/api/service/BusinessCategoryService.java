package com.yupexx.services.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.BusinessCategoryModel;
import com.yupexx.services.api.model.ServicesCategoryDetails;
import com.yupexx.services.api.model.ServicesCategoryLabel;
import com.yupexx.services.api.model.dto.BusinessCatDetailsDTO;
import com.yupexx.services.api.model.dto.BusinessCatLabelDTO;
import com.yupexx.services.api.model.dto.BusinessCatVwDetailsModelDTO;
import com.yupexx.services.api.model.view.BusinessCategoryVwModel;
import com.yupexx.services.api.model.view.BusinessCategoryVwParentModel;
import com.yupexx.services.api.repository.BusinessCategoryRepository;
import com.yupexx.services.api.repository.BusinessCategoryVwParentRepository;
import com.yupexx.services.api.repository.BusinessCategoryVwRepository;
import com.yupexx.services.api.repository.ServiceCategoryDetailRepository;
import com.yupexx.services.api.repository.ServicesCategoryLabelRepository;
import com.yupexx.services.api.service.interfaces.BusinessCategoryServiceInterface;

@Service
public class BusinessCategoryService implements BusinessCategoryServiceInterface {

	@Autowired
    BusinessCategoryRepository dao;
	@Autowired
	BusinessCategoryVwRepository daoVw;
	
	@Autowired
	BusinessCategoryVwParentRepository daoVwParent;
	
	@Autowired
	ServicesCategoryLabelRepository labelRepo;
	
	@Autowired 
	ServiceCategoryDetailRepository detailsRepo;

	@Override
	public List<BusinessCategoryVwModel> getAdCat() {
		// TODO Auto-generated method stub
		return daoVw.findAll();
	}
	
	@Override
	public Message getCat() {
		Message msg = new Message();
		
		// TODO Auto-generated method stub
		List<BusinessCatVwDetailsModelDTO> parentlist=new ArrayList<>();
		List<BusinessCategoryVwParentModel> list= daoVwParent.findAll();
		for(BusinessCategoryVwParentModel m: list ) {
			BusinessCatVwDetailsModelDTO obj=new BusinessCatVwDetailsModelDTO();
			List<BusinessCatDetailsDTO> childCatData = new ArrayList<>();
			for(BusinessCategoryModel bc: m.getData()) {
				BusinessCatDetailsDTO childCatObj = new BusinessCatDetailsDTO();
				int labelId=bc.getId();
		    	List<BusinessCatLabelDTO> labelList1 = new ArrayList<>();
				List<ServicesCategoryLabel> labelList=labelRepo.findByCategoryId(labelId);
				for(ServicesCategoryLabel l:labelList) {
					int detailId=l.getId();
					BusinessCatLabelDTO bclData = new BusinessCatLabelDTO();
					List<ServicesCategoryDetails> detailsList=detailsRepo.findByLabelId(detailId);
					bclData.setDetailsData(detailsList);
					bclData.setId(l.getId());
					bclData.setCategoryId(l.getCategoryId());
					bclData.setCreatedBy(l.getCreatedBy());
					bclData.setCreatedDate(l.getCreatedDate());
					bclData.setStatus(l.getStatus());
					bclData.setValue(l.getValue());
					bclData.setUpdatedBy(l.getUpdatedBy());
					bclData.setUpdatedDate(l.getUpdatedDate());
					labelList1.add(bclData);
				}
				childCatObj.setId(bc.getId());
				childCatObj.setCategoryName(bc.getCategoryName());
				childCatObj.setCreatedBy(bc.getCreatedBy());
				childCatObj.setCreatedDate(bc.getCreatedDate());
				childCatObj.setParentCatId(bc.getParentCatId());
				childCatObj.setStatus(bc.getStatus());
				childCatObj.setUpdatedBy(bc.getUpdatedBy());
				childCatObj.setUpdatedDate(bc.getUpdatedDate());
				childCatObj.setLabelData(labelList1);
				childCatData.add(childCatObj);
			}
			obj.setParentCatId(m.getId());
			obj.setCategoryName(m.getCategoryName());
			obj.setCreatedBy(m.getCreatedBy());
			obj.setCreatedDate(m.getCreatedDate());
			obj.setId(m.getId());
			obj.setStatus(m.getStatus());
			obj.setUpdatedBy(m.getUpdatedBy());
			obj.setUpdatedDate(m.getUpdatedDate());
			obj.setChildData(childCatData);
			parentlist.add(obj);
		}
		
		msg.setCode(200);
		msg.setContentList(parentlist);
		return msg;
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
		Optional<BusinessCategoryModel> businessCategoryModel = dao.findById(catId);
		dao.deleteById(catId);
	}

}
