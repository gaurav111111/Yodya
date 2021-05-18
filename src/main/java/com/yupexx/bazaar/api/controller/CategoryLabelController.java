package com.yupexx.bazaar.api.controller;

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
import com.yupexx.bazaar.api.model.CategoryLable;
import com.yupexx.bazaar.api.model.dto.CategoryLabelDTO;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.repository.CategoryLabelRepository;

@RestController
@CrossOrigin
public class CategoryLabelController {
	
	@Autowired
	private CategoryLabelRepository categoryLabelRepository;
	
	@RequestMapping(value = "/bazaar/categoryLabel", method = RequestMethod.GET)
	public List<CategoryLable> getAllcategoryDetails() {
		System.out.println(this.getClass().getSimpleName() + " - Get all category Label service is invoked.");
		return categoryLabelRepository.findAll();
		
	}

	@RequestMapping(value = "/bazaar/categoryLabelList/{categoryDetailsId}", method = RequestMethod.GET)
	public List<CategoryLable> getCategoryListLabelById(@PathVariable Integer categoryDetailsId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		List<CategoryLable> data1 = categoryLabelRepository.findAll();
		List<CategoryLable> data = new ArrayList<CategoryLable>();
		System.out.println("categoryDetailsId"+categoryDetailsId);
		for(CategoryLable cl:data1) {
			System.out.println("check"+cl.getCategoryId());
			if(cl.getCategoryId().equals(categoryDetailsId)) {
				System.out.println("IN");
				data.add(cl);
			}
		}
		System.out.println("data"+data.toString());
		System.out.println(data1.toString());
		return data;
	}
	
	@RequestMapping(value = "/bazaar/categoryLabel/{labelId}", method = RequestMethod.GET)
	public CategoryLable getCategoryLabelListById(@PathVariable Integer labelId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
		Optional<CategoryLable> data = categoryLabelRepository.findById(labelId);
		return data.get();
	}
	
	@RequestMapping(value = "/bazaar/categoryLabel/info", method = RequestMethod.POST)
	public Message saveCategoryLabelInfo(@RequestBody CategoryLabelDTO categoryLabelDTO) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	    Message msg = new Message();
	    System.out.println(categoryLabelDTO.toString());
	    CategoryLable cl = new CategoryLable();
	    if(categoryLabelDTO.getId()!=null && categoryLabelDTO.getLabel().length()>0) {
	    		cl.setCategoryId(categoryLabelDTO.getId());
	    		cl.setValue(categoryLabelDTO.getLabel());
	    		categoryLabelRepository.save(cl);
	    	msg.setCode(200);
	    	msg.setMessage("Data Save Sucessfully!");
	    	return msg;
	    }else {
	    	msg.setCode(400);
	    	msg.setMessage("Category And label Can not be Null!");
	    	return msg;
	    }
	}
	
	@RequestMapping(value = "/bazaar/categoryLabel/delete/{labelId}", method = RequestMethod.DELETE)
	public Message categoryDetailsDelete(@PathVariable Integer labelId) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	    Message msg = new Message();
	   
	    Optional<CategoryLable>  cdm = categoryLabelRepository.findById(labelId);
	    if(cdm.get()!=null) {
	    	if(cdm.get().getStatus()) {
	    		cdm.get().setStatus(false);
	    		categoryLabelRepository.save(cdm.get());
	    		 msg.setCode(200);
	 	       	 msg.setMessage("CategoryLabel InActive Sucessfully!");
	 	    	return msg;
	    	}else {
	    		cdm.get().setStatus(true);
	    		categoryLabelRepository.save(cdm.get());
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
	
	
	@RequestMapping(value = "/bazaar/categoryLabel/infoUpdate", method = RequestMethod.PUT)
	public Message categoryDetailsUpdate(@RequestBody CategoryLabelDTO categoryLabelDTO) {
		System.out.println(this.getClass().getSimpleName() + " - Get all category service is invoked.");
	    Message msg = new Message();
	    Optional<CategoryLable> cdm = categoryLabelRepository.findById(categoryLabelDTO.getId());
	    if(cdm.get()!=null) {
	    	cdm.get().setValue(categoryLabelDTO.getLabel());
	    	categoryLabelRepository.save(cdm.get());
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
