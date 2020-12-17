package com.yupexx.bazaar.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.service.UserService;
import com.yupexx.bazaar.console.cron.BazaarConsole;

@Service
public class UserIdentity {
	
	@Autowired
	UserService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BazaarConsole.class);

	public UserModel get(Authentication authentication) throws AuthenticationException  {
		if(authentication==null) {
			LOGGER.info("Auth is expired");
			ResponseEntity.status(HttpStatus.UNAUTHORIZED);
		}
		System.out.println(authentication.getName());
		UserModel user = service.findByEmail(authentication.getName());
		
		return user;
	}
	
	public boolean checkIfResourceAllowedForRole(Long userId,Authentication authentication) {
		if(this.get(authentication).getId()!=userId) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Not Allowed to do the operation");
		}
		
		return false;
	}
	
	public long differenceDays(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
	    Long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	    LOGGER.info("Days "+days);
	    return days;
		
	}
	
	public Date addDays(Date d, int days){
		
        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
        LOGGER.info("Date "+d);
        return d;
    }
}
