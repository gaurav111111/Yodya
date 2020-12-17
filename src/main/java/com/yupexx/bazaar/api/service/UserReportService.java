package com.yupexx.bazaar.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.UserReportModel;
import com.yupexx.bazaar.api.repository.UserReportRepository;
import com.yupexx.bazaar.api.service.interfaces.UserReportServiceInterface;

@Service
public class UserReportService implements UserReportServiceInterface {
	@Autowired
    UserReportRepository dao;

	@Override
	public UserReportModel report(UserReportModel reportuser) {
		// TODO Auto-generated method stub
		return dao.save(reportuser);
	}

	

}
