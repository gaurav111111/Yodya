package com.yupexx.bazaar.console.cron;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.yupexx.bazaar.api.model.CronModel;
import com.yupexx.bazaar.api.model.CronModel.EmailType;
import com.yupexx.bazaar.api.service.CronService;
import com.yupexx.bazaar.api.service.EmailService;
import com.yupexx.bazaar.api.service.UserService;

@SpringBootApplication
@EnableScheduling
public class BazaarConsole {

	private static final Logger LOGGER = LoggerFactory.getLogger(BazaarConsole.class);
	
	@Autowired
	CronService service;
	
	@Autowired
	EmailService email;
	
	@Autowired
	UserService userservice;
	
	@Scheduled(cron = "*/5 * * * * ?")
	public void run() throws MessagingException {
		LOGGER.info("Cron Started");
		this.sendEmail();
	}
	
	private void sendEmail() throws MessagingException {
		
		List<CronModel> cmlist = service.getAllCrons();
		
		for (CronModel cron : cmlist) {
			
			CronModel object = cron;
			
			String emailType = cron.getEmailType().toString();
			
			//USER_NEW, USER_FORGOT,USER_DELETE ,USER_PROFILE, USER_PASS, AD_APPROVED, AD_REJECTED, OTHER

			switch(emailType){
				case "USER_NEW":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.newUserRegisteration(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "USER_FORGOT":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.userForgot(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "USER_DELETE":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.userDelete(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "USER_PROFILE":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.userProfileUpdate(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "USER_PASS":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.userPassChanged(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "AD_APPROVED":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.adApproved(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "AD_REJECTED":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.adRejected(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "VERIFY_EMAIL":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.emailVerify(userservice.getUserById(cron.getCreatedBy()));
					break;
				case "VERIFY_PHONE":
					object.setIsProcessed(1);
					service.updateCron(object.getId(), object);
					email.phoneVerify(userservice.getUserById(cron.getCreatedBy()));
					break;
				default:
					LOGGER.info("No Email Type Exists");
			}
		}
		
	}

}
