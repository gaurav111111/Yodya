package com.yupexx.bazaar.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.CronModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.repository.CronRepository;
import com.yupexx.bazaar.api.service.CronService;

@RestController
@CrossOrigin
public class NotificationsController {

	@Autowired
	CronService cronservice;
	
	@Autowired
	UserIdentity identity;
	
	@Autowired
	CronRepository dao;

	
	@RequestMapping(value = "/bazaar/notifications", method = RequestMethod.GET)
	public List<CronModel> getUsers(Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Get all notification service is invoked.");
		UserModel user = identity.get(authentication);
		return cronservice.getCronsByUserId(user.getId());
	}
	
	
	@RequestMapping(value = "/bazaar/notifications/{notificationsId}", method = RequestMethod.DELETE)
	public Message deleteNotification(@PathVariable Integer notificationsId, Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Get all notification service is invoked.");
		Message msg = new Message();
		CronModel object = dao.findById(notificationsId).orElseThrow(() -> new EntityNotFoundException());
		if(object.getStatus()) {
			object.setStatus(false);
			 dao.save(object);
			 msg.setCode(200);
			 msg.setMessage("Notification InActive Successfully!");
			 return msg;

		}else {
			object.setStatus(true);
			 dao.save(object);
			 msg.setCode(200);
			 msg.setMessage("Notification Active Successfully!");
			 return msg;
		}
	}
}
