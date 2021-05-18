package com.yupexx.bazaar.api.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yupexx.bazaar.api.model.FooterContentModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.repository.FooterContentRepository;
import com.yupexx.bazaar.api.service.FooterContentService;

@RestController
@CrossOrigin
public class FooterContentController {
	
	@Autowired
	FooterContentService service;
	
	@Autowired
	FooterContentRepository dao;
	
	
	@RequestMapping(value = "/bazaar/footer", method = RequestMethod.GET)
	public List<FooterContentModel> getFooterContents() {
		System.out.println(this.getClass().getSimpleName() + " - Get all footerContent service is invoked.");
		return service.getAllFooterContent();
	}
	
	@RequestMapping(value = "/bazaar/footer/slug/{slug}", method = RequestMethod.GET)
	public Message getFooterContentsBySlug(@PathVariable String slug) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected footerContent service is invoked.");
		Message msg=new Message();
				
		FooterContentModel fc=service.getFooterContentBySlug(slug);
		if(fc!=null) {
			msg.setCode(200);
			msg.setContentList(fc);
			return msg;
		}else {
			msg.setCode(400);
			msg.setMessage("Slug not Found");
			return msg;
		}
		
	}
	
	@RequestMapping(value = "/bazaar/footer/{id}", method = RequestMethod.GET)
	public Message getFooterContentsById(@PathVariable int id) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected footerContent service is invoked.");
		FooterContentModel fc=service.getFooterContentById(id);
		Message msg=new Message();
		if(fc!=null) {
			msg.setCode(200);
			msg.setContentList(fc);
			return msg;
		}else {
			msg.setCode(400);
			msg.setMessage("Slug not Found");
			return msg;
		}
		
	}
	
	@RequestMapping(value = "/bazaar/footer", method = RequestMethod.POST)
	public Message addFooterContent(@Valid @RequestBody FooterContentModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New footerContent service is invoked.");
		
		FooterContentModel fc=service.addNewFooterContent(object);
		Message msg=new Message();
		if(fc!=null) {
			msg.setCode(200);
			msg.setContentList(fc);
			return msg;
		}else {
			msg.setCode(400);
			msg.setMessage("Slug not Found");
			return msg;
		}
	}
	
	@RequestMapping(value = "/bazaar/footer/slug/{slug}", method = RequestMethod.PUT)
	public Message updateFooterContentBySlug(@Valid @PathVariable String slug,@Valid @RequestBody FooterContentModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update footerContent service is invoked.");
		
		FooterContentModel fc=service.updateFooterContentBySlug(slug,object);
		Message msg=new Message();
		if(fc!=null) {
			msg.setCode(200);
			msg.setMessage("Update Successfully");
			msg.setContentList(fc);
			return msg;
		}else {
			msg.setCode(400);
			msg.setMessage("Slug not Found");
			return msg;
		}
	}
	
	@RequestMapping(value = "/bazaar/footer/{id}", method = RequestMethod.PUT)
	public Message updateFooterContentById(@Valid @PathVariable int id,@Valid @RequestBody FooterContentModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update footerContent service is invoked.");
		
		FooterContentModel fc=service.updateFooterContentById(id,object);
		Message msg=new Message();
		if(fc!=null) {
			msg.setCode(200);
			msg.setMessage("Update Successfully");
			msg.setContentList(fc);
			return msg;
		}else {
			msg.setCode(400);
			msg.setMessage("Slug not Found");
			return msg;
		}
	}
	
	@RequestMapping(value = "/bazaar/footer/slug/{slug}", method = RequestMethod.DELETE)
	public Message deleteFooterContentBySlug(@Valid @PathVariable String slug) {
		System.out.println(this.getClass().getSimpleName() + " - Delete footerContent service is invoked.");
		
		Message msg=new Message();
		FooterContentModel object = dao.findBySlugs(slug);
		if(object!=null) {
			if(object.getStatus()) {
				object.setStatus(false);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Slug Inactive Successfully");
				return msg;
			}else {
				object.setStatus(true);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Slug Active Successfully");
				return msg;
			}
		}else {
			msg.setCode(400);
			msg.setMessage("Slug not Found");
			return msg;
		}
	
	}
	
	@RequestMapping(value = "/bazaar/footer/{id}", method = RequestMethod.DELETE)
	public Message deleteFooterContentById(@Valid @PathVariable int id) {
		System.out.println(this.getClass().getSimpleName() + " - Delete footerContent service is invoked.");
		
		Message msg=new Message();
		FooterContentModel object = dao.findById(id);
		if(object!=null) {
			if(object.getStatus()) {
				object.setStatus(false);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Slug Inactive Successfully");
				return msg;
			}else {
				object.setStatus(true);
				dao.save(object);
				msg.setCode(200);
				msg.setMessage("Slug Active Successfully");
				return msg;
			}
		}else {
			msg.setCode(400);
			msg.setMessage("Slug not Found");
			return msg;
		}
		
	}
	

}
