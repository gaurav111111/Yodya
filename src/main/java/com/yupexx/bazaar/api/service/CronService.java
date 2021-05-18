package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.CronModel;
import com.yupexx.bazaar.api.repository.CronRepository;
import com.yupexx.bazaar.api.service.interfaces.CronInterface;

@Service
public class CronService implements CronInterface {

	@Autowired
	CronRepository dao;
	
	@Override
	public List<CronModel> getAllCrons() {
		// TODO Auto-generated method stub
		return dao.findByStatusAndIsProcessed(true,0);
	}
	
	@Override
	public List<CronModel> getCronsByUserId(Long userId) {
		// TODO Auto-generated method stub
	//	return dao.findByStatusAndCreatedByOrderByIdDesc(true,userId);
		return dao.findByCreatedByOrderByIdDesc(userId);
	}


	@Override
	public Optional<CronModel> getCronById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByIdAndStatus(id,true);
	}


	@Override
	public CronModel addCron(CronModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public CronModel updateCron(Integer cronId,CronModel object) {
		// TODO Auto-generated method stub
		CronModel cron = dao.findById(cronId).orElseThrow(() -> new EntityNotFoundException());
		cron.setIsProcessed(object.getIsProcessed());
		return dao.save(cron);
	}
	
	@Override
	public CronModel deleteCron(Integer CronId) {
		// TODO Auto-generated method stub
		CronModel object = dao.findById(CronId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
