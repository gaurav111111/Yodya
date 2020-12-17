package com.yupexx.bazaar.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.CronModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.service.CronService;

@RestController
public class NotificationsController {

	@Autowired
	CronService cronservice;
	
	@Autowired
	UserIdentity identity;
	
	@RequestMapping(value = "/bazaar/notifications", method = RequestMethod.GET)
	public List<CronModel> getUsers(Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Get all notification service is invoked.");
		UserModel user = identity.get(authentication);
		return cronservice.getCronsByUserId(user.getId());
	}
}
