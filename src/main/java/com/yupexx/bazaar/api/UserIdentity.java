package com.yupexx.bazaar.api;

import java.util.Date;
import java.util.Optional;
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
import com.yupexx.bazaar.api.model.dto.UserResponseDTO;
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
	
	
	public UserResponseDTO getUser(Authentication authentication) throws AuthenticationException  {
		if(authentication==null) {
			LOGGER.info("Auth is expired");
			ResponseEntity.status(HttpStatus.UNAUTHORIZED);
		}
		System.out.println(authentication.getName());
		UserResponseDTO userRes = new UserResponseDTO();
		UserModel user = service.findByEmail(authentication.getName());
		userRes.setId(user.getId());
		userRes.setProfilePic(user.getProfilePic());
		userRes.setEmail(user.getEmail());
		userRes.setFullName(user.getFullName());
		userRes.setDob(user.getDob());
		userRes.setCountryCode(user.getCountryCode());
		userRes.setPhoneNumber(user.getPhoneNumber());
		userRes.setRoleType(user.getRoleType());
		userRes.setAddress1(user.getAddress1());
		userRes.setAddress2(user.getAddress2());
		userRes.setZipCode(user.getZipCode());
		userRes.setCountry(user.getCountry());
		userRes.setCity(user.getCity());
		userRes.setState(user.getState());
		userRes.setSourceLogin(user.getSourceLogin());
		userRes.setSignupSteps(user.getSignupSteps());
		userRes.setIsEmailValid(user.getIsEmailValid());
		userRes.setIsMobileValid(user.getIsMobileValid());
		userRes.setStatus(user.getStatus());
		userRes.setCreatedDate(user.getCreatedDate());
		userRes.setUpdatedDate(user.getUpdatedDate());
		userRes.setnAds(user.getnAds());
		userRes.setUserRole(user.getUserRole());
		userRes.setfId(user.getFbId());
		userRes.setgId(user.getgId());
		if(user.getFbId()!=null && !(user.getFbId().isEmpty())) {
			userRes.setFbStatus(true);
		}else {
			userRes.setFbStatus(false);
		}
		if(user.getgId()!=null && !(user.getgId().isEmpty())) {
			userRes.setgStatus(true);
		}else {
			userRes.setgStatus(false);
		}
		return userRes;
	}
	
	public boolean checkIfResourceAllowedForRole(Long userId,Authentication authentication) {
		
		System.out.println("authentication"+this.get(authentication).getId());
		System.out.println("userId"+userId);
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
	
	
	public UserModel findUserByEmail(String email) {
		return service.findByEmail(email);
	}
	
	public Optional<UserModel> findUserById(long id) {
		return service.findById(id);
	}
	
	public UserModel updateUser(UserModel user) {
		return service.updateUser(user);
	}
}
