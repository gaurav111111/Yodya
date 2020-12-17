package com.yupexx.bazaar.api.service;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.config.JwtTokenUtil;
import com.yupexx.bazaar.api.model.UserModel;


@Service
public class EmailService extends EmailBaseService{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public void newUserRegisteration(Optional<UserModel> user) throws MessagingException {
		
		String Subject = "New User Registeration | Bazaar Classsified | Yupex";
		String body = "";
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
	}

	public void userForgot(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		final String token = jwtTokenUtil.generateToken(user.get());
		
		String Subject = "Email Verification | Bazaar Classsified | Yupex";
		String body = "<a href='"+env.getProperty("app.ui.name")+"/forgot/verify?_verifytokenviasignature="+token+"'>Link</a>";
		System.out.println("Link"+body);
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
	}

	public void userDelete(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		String Subject = "User Profile has been deleted | Bazaar Classsified | Yupex";
		String body = "";
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
		
	}

	public void userProfileUpdate(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		String Subject = "User Profile Updated | Bazaar Classsified | Yupex";
		String body = "";
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
	}

	public void userPassChanged(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		String Subject = "User Password Changed | Bazaar Classsified | Yupex";
		String body = "";
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
		
	}

	public void adApproved(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		String Subject = "Ad Approved | Bazaar Classsified | Yupex";
		String body = "";
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
		
	}

	public void adRejected(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		String Subject = "Ad Rejected | Bazaar Classsified | Yupex";
		String body = "";
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
		
	}

	public void emailVerify(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		final String token = jwtTokenUtil.generateToken(user.get());
		
		String Subject = "Email Verification | Bazaar Classsified | Yupex";
		String body = "<a href='"+env.getProperty("app.ui.name")+"/email/verify?_verifytokenviasignature="+token+"'>Link</a>";
		System.out.println("Link"+body);
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
		
	}

	public void phoneVerify(Optional<UserModel> user) throws MessagingException {
		// TODO Auto-generated method stub
		final String token = jwtTokenUtil.generateToken(user.get());
		
		String Subject = "Email Verification | Bazaar Classsified | Yupex";
		String body = "<a href='"+env.getProperty("app.ui.name")+"/email/phone?_verifytokenviasignature="+token+"'>Link</a>";
		System.out.println("Link"+body);
		
		
		this.sendEmail(user.get().getEmail(), Subject, body);
		
	}
}
