package com.yupexx.bazaar.api.controller;

import java.util.HashMap;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yupexx.bazaar.api.config.JwtTokenUtil;
import com.yupexx.bazaar.api.model.CronModel;
import com.yupexx.bazaar.api.model.RoleModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.dto.UserResponseDTO;
import com.yupexx.bazaar.api.model.request.JwtRequest;
import com.yupexx.bazaar.api.model.response.JwtResponse;
import com.yupexx.bazaar.api.repository.UserRepository;
import com.yupexx.bazaar.api.service.CronService;
import com.yupexx.bazaar.api.service.EmailService;
import com.yupexx.bazaar.api.service.JwtUserDetailsService;
import com.yupexx.bazaar.api.service.RoleService;
import com.yupexx.bazaar.api.service.UserService;
import com.yupexx.bazaar.console.cron.BazaarConsole;

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
	private EmailService emailService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;

	private static final Logger LOGGER = LoggerFactory.getLogger(BazaarConsole.class);
	
	@RequestMapping(value = "/bazaar/authenticate/{check}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@PathVariable String check,  @RequestBody JwtRequest authenticationRequest) throws Exception {
	//	System.out.println("Check Authentication");
		UserModel user = userDao.findByEmail(authenticationRequest.getEmail());
	//	System.out.println("user"+user.toString());
		if(user==null) {
			HashMap<String, String> map = new HashMap<>();
			map.put("message", "Email doesn't Exists");
			LOGGER.info("Email doesn't Exists");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
		else {
			if(check==null || check.isEmpty()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("message", "Please specify the frontend or backend in url");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}else {
				if(check.equalsIgnoreCase("frontend")) {
					if(user!=null && user.getRoleType()!=null) {
						//Optional<RoleModel> role= roleService.findRole(user.getRoleType());
						 if(user.getRoleType().equals("1")) {
							 HashMap<String, String> map = new HashMap<>();
								map.put("message", "Admin can not access");
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
						 }else {
							 System.out.println("authenticate______user____"+user.toString());
								authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
								final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
								final String token = jwtTokenUtil.generateToken(user);
								return ResponseEntity.ok(new JwtResponse(token));
						 }
					}else {
						System.out.println("authenticate______user____"+user.toString());
						authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
						final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
						final String token = jwtTokenUtil.generateToken(user);
						return ResponseEntity.ok(new JwtResponse(token));
					}
				}
				else {
					System.out.println("authenticate______user____"+user.toString());
					authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
					final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
					final String token = jwtTokenUtil.generateToken(user);
					return ResponseEntity.ok(new JwtResponse(token));
				}
			}
		}
	}

	@RequestMapping(value = "/bazaar/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserModel user) throws Exception {
		
		UserModel user1 = userDao.findByEmail(user.getEmail());
		if(user1!=null){
			HashMap<String, String> map = new HashMap<>();
			map.put("error", "Email Already Exists");
			LOGGER.info("Email doesn't Exists");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}else {
			String userPass = user.getPassword();
			UserModel savedUser = userDetailsService.save(user);
			CronModel cron = new CronModel();
			cron.setVia("Email");
			cron.setEmailType("USER_NEW");
			cron.setCreatedBy(savedUser.getId());
			cron.setUpdatedBy(savedUser.getId());
			service.addCron(cron);
			try {
				Optional<UserModel> userOptional = Optional.ofNullable(user);
				emailService.newUserRegisteration(userOptional);
				//sendEmail(user.getEmail());
			}catch(Exception e) {
				
			}
			authenticate(savedUser.getEmail(), userPass);
			final String token = jwtTokenUtil.generateToken(savedUser);
			return ResponseEntity.ok(new JwtResponse(token));
		}
	
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
	
	@RequestMapping(value = "/bazaar/social/google/login/{check}", method = RequestMethod.POST)
	public ResponseEntity<?> googleAuthenticationToken(@PathVariable String check, @RequestBody UserResponseDTO userModel) throws Exception {
		UserModel user = userDao.findByEmail(userModel.getEmail());
		
		
		if(user!=null) {
			if(check==null || check.isEmpty()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("message", "Please specify the frontend or backend in url");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}else {
				if(check.equalsIgnoreCase("frontend")) {
					if( user.getRoleType()!=null) {
						Optional<RoleModel> role= roleService.findRole(user.getRoleType());
						 if(role.get()!=null && !(role.get().getRoleName().isEmpty()) && role.get().getRoleName().equalsIgnoreCase("admin")) {
							 HashMap<String, String> map = new HashMap<>();
								map.put("message", "Admin can not access");
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
						 }
					}
				}
			}
		}
		UserModel user1 = new UserModel();
		if(user==null) {
			System.out.println("1");
		
		
			if (!userModel.getFullName().equals("-")) {
				user1.setFullName(userModel.getFullName());
			}
			if (!userModel.getEmail().equals("-")) {
				user1.setEmail(userModel.getEmail());
				user1.setIsEmailValid(false);
			}
			if (userModel.getPhoneNumber() > 0) {
				user1.setPhoneNumber(userModel.getPhoneNumber());
				user1.setIsMobileValid(false);
			}
			if (!userModel.getDob().equals("-")) {
				user1.setDob(userModel.getDob());
			}
			if (!userModel.getProfilePic().equals("-")) {
				user1.setProfilePic(userModel.getProfilePic());
			}
			if (!userModel.getCountryCode().equals("-")) {
				user1.setCountryCode(userModel.getCountryCode());
			}
			
			if (!userModel.getfId().equals("-")) {
				user1.setFbId(userModel.getfId());
			}
			
			if (!userModel.getgId().equals("-")) {
				user1.setgId(userModel.getgId());
				user1.setPassword(userModel.getgId());
			}
			CronModel cron = new CronModel();
			cron.setVia("Email");
			cron.setEmailType("NEW_USER");
			cron.setCreatedBy(user1.getId());
			cron.setUpdatedBy(user1.getId());
			service.addCron(cron);
			
		}
		else if(user.getgId()!=null && !(user.getgId().isEmpty()) ) {
			System.out.println("2");
			if(user.getgId().equals(userModel.getgId())) {
				System.out.println("4");
			//	authenticate(userModel.getEmail(), userModel.getgId());
				final String token = jwtTokenUtil.generateToken(user);
				return ResponseEntity.ok(new JwtResponse(token));	
			}
			else {
				System.out.println("5");
				HashMap<String, String> map = new HashMap<>();
				map.put("error", "Password is wrong!");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}
		}else if(user.getgId()==null || user.getgId().isEmpty()) {
			System.out.println("3");
			//userService.updateGoogleId(userModel.getgId(), user.getId());
			user.setgId(userModel.getgId());
			userService.updateUser(user);
		//	authenticate(userModel.getEmail(), userModel.getgId());
			final String token = jwtTokenUtil.generateToken(user);
			return ResponseEntity.ok(new JwtResponse(token));
		}
		UserModel savedUser = userDetailsService.save(user1);
		emailService.newUserSocialRegisteration(savedUser);
	//	authenticate(savedUser.getEmail(), savedUser.getgId());
		final String token = jwtTokenUtil.generateToken(savedUser);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/bazaar/social/facebook/login/{check}", method = RequestMethod.POST)
	public ResponseEntity<?> fbAuthenticationToken(@PathVariable String check, @RequestBody UserResponseDTO userModel) throws Exception {
		UserModel user = userDao.findByEmail(userModel.getEmail());
		
		if(user!=null) {
			if(check==null || check.isEmpty()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("message", "Please specify the frontend or backend in url");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}else {
				if(check.equalsIgnoreCase("frontend")) {
					if( user.getRoleType()!=null) {
						Optional<RoleModel> role= roleService.findRole(user.getRoleType());
						 if(role.get()!=null && !(role.get().getRoleName().isEmpty()) && role.get().getRoleName().equalsIgnoreCase("admin")) {
							 HashMap<String, String> map = new HashMap<>();
								map.put("message", "Admin can not access");
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
						 }
					}
				}
			}
		}
		
		UserModel user1 = new UserModel();
		if(user==null) {
			if (!userModel.getFullName().equals("-")) {
				user1.setFullName(userModel.getFullName());
			}
			if (!userModel.getEmail().equals("-")) {
				user1.setEmail(userModel.getEmail());
				user1.setIsEmailValid(false);
			}
			if (userModel.getPhoneNumber() > 0) {
				user1.setPhoneNumber(userModel.getPhoneNumber());
				user1.setIsMobileValid(false);
			}
			if (!userModel.getDob().equals("-")) {
				user1.setDob(userModel.getDob());
			}
			if (!userModel.getProfilePic().equals("-")) {
				user1.setProfilePic(userModel.getProfilePic());
			}
			if (!userModel.getCountryCode().equals("-")) {
				user1.setCountryCode(userModel.getCountryCode());
			}
			
			if (!userModel.getfId().equals("-")) {
				user1.setFbId(userModel.getfId());
				user1.setPassword(userModel.getfId());
			}
			if (!userModel.getgId().equals("-")) {
				user1.setgId(userModel.getgId());
			}
		}
		else if(user.getFbId()!=null && !(user.getFbId().isEmpty()) ) {
			System.out.println("2");
			if(user.getFbId().equals(userModel.getfId())) {
				System.out.println("4");
			
				final String token = jwtTokenUtil.generateToken(user);
				return ResponseEntity.ok(new JwtResponse(token));	
			}
			else {
				System.out.println("5");
				HashMap<String, String> map = new HashMap<>();
				map.put("error", "Password is wrong!");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
			}
		}else if(user.getFbId()==null || user.getFbId().isEmpty()) {
			System.out.println("3");
			user.setFbId(userModel.getfId());
			userService.updateUser(user);
			final String token = jwtTokenUtil.generateToken(user);
			return ResponseEntity.ok(new JwtResponse(token));
		}
		UserModel savedUser = userDetailsService.save(user1);
		emailService.newUserSocialRegisteration(savedUser);
		final String token = jwtTokenUtil.generateToken(savedUser);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
