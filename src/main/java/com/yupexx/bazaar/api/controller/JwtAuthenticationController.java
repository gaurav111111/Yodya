package com.yupexx.bazaar.api.controller;

import com.yupexx.bazaar.api.config.JwtTokenUtil;
import com.yupexx.bazaar.api.model.CronModel;
import com.yupexx.bazaar.api.model.request.JwtRequest;
import com.yupexx.bazaar.api.model.response.JwtResponse;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.repository.UserRepository;
import com.yupexx.bazaar.api.service.CronService;
import com.yupexx.bazaar.api.service.JwtUserDetailsService;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private CronService service;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@RequestMapping(value = "/bazaar/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		UserModel user = userDao.findByEmail(authenticationRequest.getEmail());
		if(user==null) {
			HashMap<String, String> map = new HashMap<>();
			map.put("error", "Email doesn't Exists");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/bazaar/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserModel user) throws Exception {
		UserModel user1 = userDao.findByEmail(user.getEmail());
		if(user1!=null){
			HashMap<String, String> map = new HashMap<>();
			map.put("error", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
		String userPass = user.getPassword();
		UserModel savedUser = userDetailsService.save(user);
		CronModel cron = new CronModel();
		cron.setVia(com.yupexx.bazaar.api.model.CronModel.Via.EMAIL);
		cron.setEmailType(com.yupexx.bazaar.api.model.CronModel.EmailType.USER_NEW);
		cron.setCreatedBy(savedUser.getId());
		cron.setUpdatedBy(savedUser.getId());
		service.addCron(cron);
		try {
			sendEmail1();
		}catch(Exception e) {
			
		}
		
		authenticate(savedUser.getEmail(), userPass);
		final String token = jwtTokenUtil.generateToken(savedUser);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	 void sendEmail1() {
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("gauravpal1077@gmail.com", "gauravpal7710@gmail.com");

	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Hello World \n Spring Boot Email");

	        javaMailSender.send(msg);

	    }
	private void authenticate(String username, String password) throws BadCredentialsException  {
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/bazaar/social/google/login", method = RequestMethod.POST)
	public ResponseEntity<?> googleAuthenticationToken(@RequestBody UserModel userModel) throws Exception {
		UserModel user = userDao.findByEmail(userModel.getEmail());
		if(user==null) {
			user = userDetailsService.saveGoogleUser(userModel);
			CronModel cron = new CronModel();
			cron.setVia(com.yupexx.bazaar.api.model.CronModel.Via.EMAIL);
			cron.setEmailType(com.yupexx.bazaar.api.model.CronModel.EmailType.USER_NEW);
			cron.setCreatedBy(user.getId());
			cron.setUpdatedBy(user.getId());
			service.addCron(cron);
		}
		authenticate(userModel.getEmail(), userModel.getgId());
		final String token = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/bazaar/social/facebook/login", method = RequestMethod.POST)
	public ResponseEntity<?> fbAuthenticationToken(@RequestBody UserModel userModel) throws Exception {
		UserModel user = userDao.findByEmail(userModel.getEmail());
	//	UserModel user = userDao.findByEmail(userModel.getFbId());
		if(user==null) {
			user = userDetailsService.saveFBUser(userModel);
			CronModel cron = new CronModel();
			cron.setVia(com.yupexx.bazaar.api.model.CronModel.Via.EMAIL);
			cron.setEmailType(com.yupexx.bazaar.api.model.CronModel.EmailType.USER_NEW);
			cron.setCreatedBy(user.getId());
			cron.setUpdatedBy(user.getId());
			service.addCron(cron);
		}
		authenticate(userModel.getEmail(), userModel.getFbId());
		final String token = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
