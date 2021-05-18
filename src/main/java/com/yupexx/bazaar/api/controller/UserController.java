package com.yupexx.bazaar.api.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.CronModel;
import com.yupexx.bazaar.api.model.RoleModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.UserReportModel;
import com.yupexx.bazaar.api.model.dto.PhoneOtpDto;
import com.yupexx.bazaar.api.model.dto.UserChangePasswordDTO;
import com.yupexx.bazaar.api.model.dto.UserResponseDTO;
import com.yupexx.bazaar.api.model.request.UpdateUserRequest;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.model.view.SellerAdCountVwModel;
import com.yupexx.bazaar.api.service.CronService;
import com.yupexx.bazaar.api.service.EmailService;
import com.yupexx.bazaar.api.service.JwtUserDetailsService;
import com.yupexx.bazaar.api.service.RoleService;
import com.yupexx.bazaar.api.service.UserReportService;
import com.yupexx.bazaar.api.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserService service;
	
	@Autowired
	RoleService roleService;

	@Autowired
	UserReportService reportservice;

	@Autowired
	CronService cronservice;

	@Autowired
	UserIdentity identity;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private EntityManager entityManager;
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/bazaar/users/similar/sellers", method = RequestMethod.GET)
	public List<SellerAdCountVwModel> getSimilarSellers() {
		System.out.println(this.getClass().getSimpleName() + " - Get all Similar Sellers service is invoked.");
		return service.getSimilarSellers();
	}

	@RequestMapping(value = "/bazaar/users", method = RequestMethod.GET)
	public List<UserModel> getUsers(Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Get all users service is invoked.");
		UserModel user = identity.get(authentication);
		Optional<RoleModel> role= roleService.findRole(user.getRoleType());
		if(role!=null) {
			if(role.get().getRoleName().equalsIgnoreCase("admin")) {
				System.out.println("Admin---------------------");
				return service.getUsers(user);
			}else {
				return service.getUsers();
			}
		}else {
			return service.getUsers();
		}
	}

	@RequestMapping(value = "/bazaar/users/{userId}", method = RequestMethod.GET)
	public UserResponseDTO getuserById(@PathVariable Long userId, Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Get user details by id is invoked.");
		// user resource check
		System.out.println("userId"+userId);
		System.out.println("authentication"+authentication);
		try {
			identity.checkIfResourceAllowedForRole(userId, authentication);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("not allowed"+ e.getMessage());
		}
		return identity.getUser(authentication);
	}

	@RequestMapping(value = "/bazaar/users", method = RequestMethod.POST)
	public UserModel createuser(@RequestBody UserModel user) {
		System.out.println(this.getClass().getSimpleName() + " - Create new user method is invoked.");
		return service.addUser(user);
	}

	@RequestMapping(value = "/bazaar/users/{userId}", method = RequestMethod.PUT)
	public UpdateUserRequest updateuser(@RequestBody UpdateUserRequest updUser, @PathVariable Long userId,
			Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update user details by id is invoked.");

		// user resource check
		identity.checkIfResourceAllowedForRole(userId, authentication);

		UserModel user = identity.get(authentication);
		System.out.println("updUser---------------------"+updUser.toString());
		System.out.println("user---------------------"+user.toString());
		if (!updUser.getFullName().equals("-")) {
			user.setFullName(updUser.getFullName());
		}
		if (!updUser.getEmail().equals("-")) {
			user.setEmail(updUser.getEmail());
			user.setIsEmailValid(false);
		}
		if (updUser.getPhoneNumber() > 0) {
			user.setPhoneNumber(updUser.getPhoneNumber());
			user.setIsMobileValid(false);
		}
		if (!updUser.getDob().equals("-")) {
			user.setDob(updUser.getDob());
		}
		if (!updUser.getProfilePic().equals("-")) {
			user.setProfilePic(updUser.getProfilePic());
		}
		if (!updUser.getCountryCode().equals("-")) {
			user.setCountryCode(updUser.getCountryCode());
		}
		if (!updUser.getFbId().equals("-")) {
			user.setFbId(updUser.getFbId());
		}
		if (!updUser.getgId().equals("-")) {
			user.setgId(updUser.getgId());
		}

		CronModel cron = new CronModel();
		cron.setVia("EMAIL");
		cron.setEmailType("USER_PROFILE");
		cron.setCreatedBy(user.getId());
		cron.setUpdatedBy(user.getId());
		cronservice.addCron(cron);

		// Required for the "where" clause in the sql query template.
		user.setId(userId);
		UserModel updatedUser = service.updateUser(user);
		if(updatedUser.getIsEmailValid()==false) {
			emailService.emailVerify(updatedUser);
		}
		UpdateUserRequest updateRes = new UpdateUserRequest();
		updateRes.setFullName(updatedUser.getFullName());
		updateRes.setEmail(updatedUser.getEmail());
		updateRes.setPhoneNumber(updatedUser.getPhoneNumber());
		updateRes.setDob(updatedUser.getDob());

		return updateRes;
	}

	@RequestMapping(value = "/bazaar/users/{userId}", method = RequestMethod.PATCH)
	public UserModel updatePassword(@Valid @RequestBody UserChangePasswordDTO updUser, @PathVariable Long userId,
			Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Update user details by id is invoked.");
		// user resource check
		identity.checkIfResourceAllowedForRole(userId, authentication);
		UserModel user = identity.get(authentication);
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), updUser.getCurrentPassword()));
		// Required for the "where" clause in the sql query template.
		user.setPassword(updUser.getNewPassword());
		user.setCreatedDate(new Timestamp(new Date().getTime()));

		CronModel cron = new CronModel();
		cron.setVia("EMAIL");
		cron.setEmailType("USER_PASS");
		cron.setCreatedBy(user.getId());
		cron.setUpdatedBy(user.getId());
		cronservice.addCron(cron);
		UserModel savedUser= userDetailsService.save(user);
		emailService.updatePassword(savedUser);
		return savedUser;
	}
	@RequestMapping(value = "/bazaar/users/{userId}", method = RequestMethod.DELETE)
	public void deleteuserById(@PathVariable Long userId, Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Delete user by id is invoked.");
      System.out.println("userId"+userId);
      System.out.println("auth"+authentication);
		UserModel user = identity.get(authentication);
		CronModel cron = new CronModel();
		cron.setVia("EMAIL");
		cron.setEmailType("USER_INACTIVE");
		cron.setCreatedBy(user.getId());
		cron.setUpdatedBy(user.getId());
		cronservice.addCron(cron);

		// user resource check
		identity.checkIfResourceAllowedForRole(userId, authentication);
		service.deleteUser(user);
	}

	@RequestMapping(value = "/bazaar/users/report/seller", method = RequestMethod.POST)
	public UserReportModel reportUser(@Valid @RequestBody UserReportModel reportUser, Authentication authentication)
			throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - User Seller Report by User is invoked.");

		// user resource check
		UserModel user = identity.get(authentication);

		// Required for the "where" clause in the sql query template.
		reportUser.setCreatedBy(user.getId());
		reportUser.setUpdatedBy(user.getId());

		return reportservice.report(reportUser);
	}

	@RequestMapping(value = "/bazaar/users/email/verify", method = RequestMethod.PATCH)
	public Map<String, String> emailVerify(Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - User Email Verification is invoked.");
		// user resource check
		UserModel user = identity.get(authentication);
 if(user !=null) {
	 Optional<UserModel> userOption =Optional.ofNullable(user);
	 emailService.emailVerify(userOption.get());
 }
//		CronModel cron = new CronModel();
//		cron.setVia(com.yupexx.bazaar.api.model.CronModel.Via.EMAIL);
//		cron.setEmailType(com.yupexx.bazaar.api.model.CronModel.EmailType.VERIFY_EMAIL);
//		cron.setCreatedBy(user.getId());
//		cron.setUpdatedBy(user.getId());
//		cronservice.addCron(cron);
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "We have sent email verification request on email-id");
		return map;
	}

	
	
	
	@RequestMapping(value = "/bazaar/users/email/activation", method = RequestMethod.PATCH)
	public Message emailActivation(Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - User Email Verification is invoked.");
		// user resource check
		Message message = new Message();
		UserModel user = identity.get(authentication);
		
		
 if(user !=null) {
	    user.setIsEmailValid(true);
		UserModel updatedUser = service.updateUser(user);
		message.setCode(200);
		message.setContent("Your Email Successfully Verified!");
		return message;
 }else {
		message.setCode(400);
		message.setContent("There is a problem while saving your information!");
		return message;
  }
	}
	
	@RequestMapping(value = "/bazaar/user/resetPassword", method = RequestMethod.PATCH)
	public Message resetPasswordByAdmin(@RequestBody UpdateUserRequest updUser, Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - User Email Verification is invoked.");
		// user resource check
		Message message = new Message();
		UserModel user = identity.get(authentication);
		
		if(user.getRoleType()!=null) {
			Optional<RoleModel> role= roleService.findRole(user.getRoleType());
			if(role!=null) {
				if(role.get().getRoleName().equalsIgnoreCase("admin")) {
					if(updUser.getEmail()!=null && !(updUser.getEmail().equalsIgnoreCase("-"))) {
						UserModel user1 =identity.findUserByEmail(updUser.getEmail());
						if(user1!=null) {
							emailService.userForgotPin(user1);
							message.setCode(200);
							message.setContent("Password reset Sucessfully!");
							return message;
						}else {
							message.setCode(400);
							message.setContent("EmailId not registered in DB!");
							return message;
						}	
					}else {
						message.setCode(400);
						message.setContent("Correct Email Required!");
						return message;
					}
				}else {
					message.setCode(400);
					message.setContent("Only Admin reset the Password!");
					return message;
				}
			}else {
				message.setCode(400);
				message.setContent("Only Admin reset the Password!");
				return message;
			}
		}else {
			message.setCode(400);
			message.setContent("Only Admin reset the Password!");
			return message;
		}
	}
	
	@RequestMapping(value = "/bazaar/users/phone/verify", method = RequestMethod.PATCH)
	public Message phoneVerify(@Valid @RequestBody PhoneOtpDto otp, Authentication authentication) throws Exception {
	
		Message message = new Message();
		System.out.println(this.getClass().getSimpleName() + " - User Email Verification is invoked.");
		// user resource check
		UserModel user = identity.get(authentication);
		
		if(user!=null) {
			if(otp.getOtp()!=null) {
				if(otp.getOtp()==123456) {
					user.setIsMobileValid(true);
					UserModel updatedUser = service.updateUser(user);
					message.setCode(200);
					message.setContent("Your phone Number Successfully Verified!");
					return message;
					
				}else {
					message.setCode(400);
					message.setContent("Otp did not Matched!");
					return message;
				}
			}else {
				message.setCode(400);
				message.setContent("Please Enter Correct Otp");
				return message;
			}
		}

//		CronModel cron = new CronModel();
//		cron.setVia(com.yupexx.bazaar.api.model.CronModel.Via.EMAIL);
//		cron.setEmailType(com.yupexx.bazaar.api.model.CronModel.EmailType.VERIFY_PHONE);
//		cron.setCreatedBy(user.getId());
//		cron.setUpdatedBy(user.getId());
//		cronservice.addCron(cron);

		
		return message;
	}

	@RequestMapping(value = "/bazaar/users/forgot/password", method = RequestMethod.PATCH)
	public Map<String, String> forgotPassword(String email) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Forgot Passoword is invoked.");

		// user resource check
		UserModel user = service.findByEmail(email);
		if (user == null) {
			throw new Exception("Email doesn't Exists");
		}
		emailService.userForgotPin(user);
//		CronModel cron = new CronModel();
//		cron.setVia(com.yupexx.bazaar.api.model.CronModel.Via.EMAIL);
//		cron.setEmailType(com.yupexx.bazaar.api.model.CronModel.EmailType.USER_FORGOT);
//		cron.setCreatedBy(user.getId());
//		cron.setUpdatedBy(user.getId());
//		cronservice.addCron(cron);
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "We have sent Email for further instructions");
		return map;
		
	}
	
	@RequestMapping(value = "/bazaar/users/access", method = RequestMethod.PATCH)
	public Message userActiveInActive(@RequestBody UpdateUserRequest updUser, Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - Forgot Passoword is invoked.");
		Message message = new Message();
		
		if(authentication==null)
			throw new Exception("JWT Token Required");
		
		UserModel user = identity.get(authentication);
		if (user == null) {
			throw new Exception("Email doesn't Exists");
		}
		else {
			if(user.getRoleType()!=null) {
				Optional<RoleModel> role= roleService.findRole(user.getRoleType());
				if(role!=null) {
					if(role.get().getRoleName().equalsIgnoreCase("admin")) {		
							if(updUser.getId()!=0 ) {
								Optional<UserModel> user1 =identity.findUserById(updUser.getId());
								
								if(user1!=null) {
									user1.get().setStatus(updUser.getStatus());
									identity.updateUser(user1.get());
									message.setCode(200);
									message.setContent("Status Change Sucessfully!");
									return message;
								}else {
									message.setCode(400);
									message.setContent("EmailId not registered in DB!");
									return message;
								}	
							}
							else {
								message.setCode(400);
								message.setContent("Correct UserId  Required!");
								return message;	
							}
						
					}else {
						message.setCode(400);
						message.setContent("Only Admin Can Change Access!");
						return message;
					}
				}else {
					message.setCode(400);
					message.setContent("Only Admin Can Change Access!");
					return message;
				}
			}else {
				message.setCode(400);
				message.setContent("Only Admin Can Change Access!");
				return message;
			}
		}
		
	}
	
	
	

}
