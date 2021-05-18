package com.yupexx.bazaar.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.ads.AdPostAnalyticsModel;
import com.yupexx.bazaar.api.model.request.AnalyticsRequest;
import com.yupexx.bazaar.api.service.AnalyticsService;

@RestController
@CrossOrigin
public class AnalyticsController {

	@Autowired
	UserIdentity identity;

	@Autowired
	AnalyticsService service;

	@RequestMapping(value = "/bazaar/analytics/ads", method = RequestMethod.POST)
	public AdPostAnalyticsModel addNewAnalytics(@Valid @RequestBody AnalyticsRequest ranalytics,Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - POST Analytics service is invoked.");

		UserModel user = identity.get(authentication);
		AdPostAnalyticsModel analytics = new AdPostAnalyticsModel();
		analytics.setAdId(ranalytics.getAdId());
		analytics.setUserMasterId(ranalytics.getUserMasterId());
		analytics.setCounter(1);
		

		if(ranalytics.getType().contentEquals("click")) {
			analytics.setType(ranalytics.getType());
		}else if(ranalytics.getType().contentEquals("req")) {
			analytics.setType(ranalytics.getType());
		}else if(ranalytics.getType().contentEquals("view")) {
			analytics.setType(ranalytics.getType());
		}else {
			throw new Exception("Type is not Defined");
		}

		analytics.setStatus(true);
		analytics.setCreatedBy(user.getId());
		analytics.setUpdatedBy(user.getId());

		return service.addNewAnalytics(analytics);
	}
}
