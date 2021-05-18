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

import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.services.api.model.ServicesCategoryLabel;
import com.yupexx.services.api.model.dto.ServiceCategoryLabelDTO;
import com.yupexx.services.api.repository.ServicesCategoryLabelRepository;

@RestController
@CrossOrigin
public class ServicesCategoryLabelController {
	
	@Autowired
	private ServicesCategoryLabelRepository repository;
	
	@RequestMapping(value = "/services/categoryLabel", method = RequestMethod.GET)
	public List<ServicesCategoryLabel> getAllServiceCategoryLabels() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Service category Label service is invoked.");
		return repository.findAll();
		
	}

	@RequestMapping(value = "/services/categoryLabelList/{catId}", method = RequestMethod.GET)
	public List<ServicesCategoryLabel> getServiceCategoryListLabelByCategoryId(@PathVariable Integer catId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all service category label service is invoked.");
		List<ServicesCategoryLabel> data1 = repository.findAll();
		List<ServicesCategoryLabel> data = new ArrayList<ServicesCategoryLabel>();
		System.out.println("categoryDetailsId"+catId);
		for(ServicesCategoryLabel cl:data1) {
			System.out.println("check"+cl.getCategoryId());
			if(cl.getCategoryId().equals(catId)) {
				System.out.println("IN");
				data.add(cl);
			}
		}
		System.out.println("data"+data.toString());
		System.out.println(data1.toString());
		return data;
	}
	
	@RequestMapping(value = "/services/categoryLabel/{labelId}", method = RequestMethod.GET)
	public ServicesCategoryLabel getCategoryLabelListById(@PathVariable Integer labelId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		Optional<ServicesCategoryLabel> data = repository.findById(labelId);
		return data.get();
	}
	
	@RequestMapping(value = "/services/categoryLabel/info", method = RequestMethod.POST)
	public Message saveCategoryLabelInfo(@RequestBody ServiceCategoryLabelDTO categoryLabelDTO) {
		System.out.println(this.getClass().getSimpleName() + " - Get all service category label service is invoked.");
	    Message msg = new Message();
	    System.out.println(categoryLabelDTO.toString());
	    ServicesCategoryLabel cl = new ServicesCategoryLabel();
	    if(categoryLabelDTO.getId()!=null && categoryLabelDTO.getLabel().length()>0) {
	    		cl.setCategoryId(categoryLabelDTO.getId());
	    		cl.setValue(categoryLabelDTO.getLabel());
	    		repository.save(cl);
	    	msg.setCode(200);
	    	msg.setMessage("Data Save Sucessfully!");
	    	return msg;
	    }else {
	    	msg.setCode(400);
	    	msg.setMessage("Category And label Can not be Null!");
	    	return msg;
	    }
	}
	
	@RequestMapping(value = "/services/categoryLabel/delete/{labelId}", method = RequestMethod.DELETE)
	public Message categoryDetailsDelete(@PathVariable Integer labelId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	    Message msg = new Message();
	   
	    Optional<ServicesCategoryLabel>  cdm = repository.findById(labelId);
	    if(cdm.get()!=null) {
	    	if(cdm.get().getStatus()) {
	    		cdm.get().setStatus(false);
	    		repository.save(cdm.get());
	    		 msg.setCode(200);
	 	       	 msg.setMessage("CategoryLabel InActive Sucessfully!");
	 	    	return msg;
	    	}else {
	    		cdm.get().setStatus(true);
	    		repository.save(cdm.get());
	    		 msg.setCode(200);
	 	       	 msg.setMessage("CategoryLabel Active Sucessfully!");
	 	    	return msg;
	    	}
	    }else {
	    	msg.setCode(400);
	    	msg.setMessage("CategoryLabel not found by Id!");
	    	return msg;
	    }
	}
	
	
	@RequestMapping(value = "/services/categoryLabel/infoUpdate", method = RequestMethod.PUT)
	public Message categoryDetailsUpdate(@RequestBody ServiceCategoryLabelDTO categoryLabelDTO) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	    Message msg = new Message();
	    Optional<ServicesCategoryLabel> cdm = repository.findById(categoryLabelDTO.getId());
	    if(cdm.get()!=null) {
	    	cdm.get().setValue(categoryLabelDTO.getLabel());
	    	repository.save(cdm.get());
	    	msg.setCode(200);
	    	msg.setMessage("Label updated Sucessfully!");
	    	return msg;
	    }else {
	    	msg.setCode(400);
	    	msg.setMessage("Label not found by Id!");
	    	return msg;
	    }
	}

}
