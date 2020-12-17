package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.CronModel;

public interface CronInterface {

	List<CronModel> getAllCrons();

	Optional<CronModel> getCronById(Integer id);
	
	CronModel addCron(CronModel object);

	CronModel updateCron(Integer CronId, CronModel object);

	CronModel deleteCron(Integer CronId);

	List<CronModel> getCronsByUserId(Long userId);

}
