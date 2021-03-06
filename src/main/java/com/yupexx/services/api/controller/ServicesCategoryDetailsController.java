package com.yupexx.services.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.dto.CategoryDetailsDTO;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.ServicesCategoryDetails;
import com.yupexx.services.api.model.dto.ServiceCategoryLabelDTO;
import com.yupexx.services.api.repository.ServiceCategoryDetailRepository;
import com.yupexx.services.api.service.ServiceCategoryDetailsService;

@RestController
@CrossOrigin
public class ServicesCategoryDetailsController {
	

	
	@Autowired
	ServiceCategoryDetailRepository dao;
	
	@RequestMapping(value = "/services/categoryDetails", method = RequestMethod.GET)
	public List<ServicesCategoryDetails> getAllcategoryDetails() {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		return dao.findAll();
	}
	
	@RequestMapping(value = "/services/categoryDetailByLabel/{labelId}", method = RequestMethod.GET)
	public List<ServicesCategoryDetails> getAllcategoryDetailsByLabel(@PathVariable Integer labelId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		
		List<ServicesCategoryDetails> data = new ArrayList<ServicesCategoryDetails>();
		List<ServicesCategoryDetails> data1=dao.findAll();
		for(ServicesCategoryDetails cd: data1) {
			if(cd.getLabelId()==labelId) {
				data.add(cd);
			}
		}
		return data;
	}
	
	@RequestMapping(value = "/services/categoryDetails/{categoryDetailsId}", method = RequestMethod.GET)
	public List<ServicesCategoryDetails> getCategoryDetailsById(@PathVariable Integer categoryDetailsId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		System.out.println(categoryDetailsId+"categoryDetailsId");
		List<ServicesCategoryDetails> data = new ArrayList<ServicesCategoryDetails>();
		List<ServicesCategoryDetails> data1=dao.findAll();
		for(ServicesCategoryDetails cd: data1) {
			if(cd.getCategoryId()==categoryDetailsId) {
				data.add(cd);
			}
		}
		
		System.out.println("------------"+data.toString());
		return data;
	}

	@RequestMapping(value = "/services/categoryDetails/info", method = RequestMethod.POST)
	public Message getCategoryDetailsInfo(@RequestBody CategoryDetailsDTO categoryDetailsDTO) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	    Message msg = new Message();
	 System.out.println(categoryDetailsDTO.toString());
	    if(categoryDetailsDTO.getId()!=null && categoryDetailsDTO.getLabelId()!=null && categoryDetailsDTO.getValue().size()>0) {
	    	for(int i=0; i<categoryDetailsDTO.getValue().size(); i++) {
	    		 ServicesCategoryDetails cdm = new ServicesCategoryDetails();
	    		 cdm.setCategoryId(categoryDetailsDTO.getId());
	    		 cdm.setValue(categoryDetailsDTO.getValue().get(i));
	    		 cdm.setLabelId(categoryDetailsDTO.getLabelId());
	    		 dao.save(cdm);
	    	}	
	    	msg.setCode(200);
	    	msg.setMessage("Data Save Sucessfully!");
	    	return msg;
	    }else {
	    	msg.setCode(400);
	    	msg.setMessage("Category, labelId And value Can not be Null!");
	    	return msg;
	    }
	}
	
	@RequestMapping(value = "/services/categoryDetails/info/{categoryDetailsId}", method = RequestMethod.DELETE)
	public Message categoryDetailsDelete(@PathVariable Integer categoryDetailsId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	    Message msg = new Message();
	   
	    Optional<ServicesCategoryDetails>  cdm = dao.findById(categoryDetailsId);
	    if(cdm.get()!=null) {
	    	if(cdm.get().getStatus()) {
	    		cdm.get().setStatus(false);
	    		 dao.save(cdm.get());
	    		 msg.setCode(200);
	 	       	 msg.setMessage("CategoryDetails InActive Sucessfully!");
	 	    	return msg;
	    	}else {
	    		cdm.get().setStatus(true);
	    		 dao.save(cdm.get());
	    		 msg.setCode(200);
	 	       	 msg.setMessage("CategoryDetails Active Sucessfully!");
	 	    	return msg;
	    	}
	    }else {
	    	msg.setCode(400);
	    	msg.setMessage("CategoryDetails not found by Id!");
	    	return msg;
	    }
	}
	
	@RequestMapping(value = "/services/categoryDetails/infoUpdate", method = RequestMethod.PUT)
	public Message categoryDetailsUpdate(@RequestBody ServiceCategoryLabelDTO serviceCategoryLabelDTO) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	   //    System.out.println("value"+value);
		Message msg = new Message();
	    Optional<ServicesCategoryDetails> cdm = dao.findById(serviceCategoryLabelDTO.getId());
	    if(cdm.get()!=null) {
	    	cdm.get().setValue(serviceCategoryLabelDTO.getLabel());
	    	 dao.save(cdm.get());
	    	msg.setCode(200);
	    	msg.setMessage("Value updated Sucessfully!");
	    	return msg;
	    }else {
	    	msg.setCode(400);
	    	msg.setMessage("CategoryDetails not found by Id!");
	    	return msg;
	    }
	}
	

}
