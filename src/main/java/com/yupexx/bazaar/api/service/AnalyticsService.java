package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.BlogModel;
import com.yupexx.bazaar.api.model.ads.AdPostAnalyticsModel;
import com.yupexx.bazaar.api.repository.AnalyticsRepository;
import com.yupexx.bazaar.api.repository.BlogRepository;
import com.yupexx.bazaar.api.service.interfaces.AnalyticsInterface;
import com.yupexx.bazaar.api.service.interfaces.BlogInterface;

@Service
public class AnalyticsService implements AnalyticsInterface {
	
	@Autowired
	AnalyticsRepository dao;

	@Override
	public List<AdPostAnalyticsModel> getAllAnalyticss() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional getAnalyticsById(Integer AnalyticsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdPostAnalyticsModel addNewAnalytics(AdPostAnalyticsModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}

	@Override
	public AdPostAnalyticsModel updateAnalytics(Integer AnalyticsId, AdPostAnalyticsModel object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdPostAnalyticsModel deleteAnalytics(Integer AnalyticsId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
