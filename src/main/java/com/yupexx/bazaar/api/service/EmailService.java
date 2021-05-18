package com.yupexx.bazaar.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.config.JwtTokenUtil;
import com.yupexx.bazaar.api.model.UserModel;


@Service
public class EmailService extends EmailBaseService{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	

	public void newUserRegisteration(Optional<UserModel> user) throws MessagingException {
		System.out.println("check how much time");
		String Subject = "Welcome to Yoyda";
	
		String name= user.get().getFullName();
		final String token = jwtTokenUtil.generateToken(user.get());
		String body1="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <title>Yoyda</title>\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width\"/>\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"      body {\r\n" + 
				"        margin: 0;\r\n" + 
				"        mso-line-height-rule: exactly;\r\n" + 
				"        padding: 0;\r\n" + 
				"        min-width: 100%;\r\n" + 
				"      }\r\n" + 
				"      table {\r\n" + 
				"        border-collapse: collapse;\r\n" + 
				"        border-spacing: 0;\r\n" + 
				"      }\r\n" + 
				"      td {\r\n" + 
				"        padding: 0;\r\n" + 
				"        vertical-align: top;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"      .image1 {  font-size: 12px;\r\n" + 
				"        Margin-bottom: 24px;\r\n" + 
				"        mso-line-height-rule: at-least;\r\n" + 
				"      }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body style=\"margin: 0;min-width: 100%;background-color: #fff\">\r\n" + 
				"  <center class=\"wrapper\" style=\"display: table;table-layout: fixed;width: 100%;min-width: 600px;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;background-color: #fff\">\r\n" + 
				"    <table cellpadding=\"0\" cellspacing=\"0\" class=\"header centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"logo\" style=\"padding: 32px 0px; vertical-align: top;\">\r\n" + 
				"            <div class=\"logo-center\" style=\"text-align: center\" align=\"center\" id=\"email-header\"><a href=\"https://www.yoyda.com\" target=\"_blank\">\r\n" + 
				"              <img style=\"border: 0;display: block;Margin-left: auto;Margin-right: auto;\" src=\"https://www.yoyda.com/bazaar/assets/images/logo/logo_bazaar.png\" alt=\"\"></a>\r\n" + 
				"            </div>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"    <table class=\"centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"border\" style=\"padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;width: 1px\">​</td>\r\n" + 
				"          <td style=\"padding: 0;vertical-align: top\">\r\n" + 
				"            <table class=\"one-col\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px;background-color: #ffffff;font-size: 14px;table-layout: fixed\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td class=\"column\" style=\"padding: 0;vertical-align: top;text-align: left\">\r\n" + 
				"                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"contents\" style=\"border-collapse: collapse;border-spacing: 0; border:1px solid #ededed; table-layout: fixed;width: 100%\">\r\n" + 
				"                      <tbody>\r\n" + 
				"                        <tr>\r\n" + 
				"                          <td class=\"padded\" style=\"padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px;word-break: break-word;word-wrap: break-word\">\r\n" + 
				"                            <p style=\"color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 20px; Margin-top: 24px; \">Dear "+name+",</p>\r\n" + 
				"                            <h1 style=\"Margin-top: 0;color: #565656;font-weight: 700;font-size: 26px;Margin-bottom: 18px;font-family: sans-serif;line-height: 32px; text-align: center\">Welcome to your Yoyda!</h1>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 40px;text-align: center\">Simply verify your email address so we know this account belongs to you.</p>\r\n" + 
				"                            <p style=\"text-align:center;\"><a href='"+env.getProperty("app.ui.name")+"/email/verify?_verifytokenviasignature="+token+"' target=\"_blank\" style=\"text-transform:uppercase; font-family: sans-serif;text-decoration:none; background-color:#fcab52; color:#ffffff; display:inline-block; padding:10px 15px; border-radius:3px;\">verify Email Address</a></p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 50px;text-align: center\">\r\n" + 
				"                              If you didn’t initiate this request, you can ignore this email message.\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"padding-top: 50px;color:#565656;font-family: sans-serif;font-size: 16px;line-height: 20px;\">\r\n" + 
				"                              All the best,\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"color:#565656;font-family: sans-serif;font-size: 16px;padding-bottom: 30px;\">\r\n" + 
				"                              <strong>Team Yoyda</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                          </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                      </tbody>\r\n" + 
				"                    </table>\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:20px; text-align: center;\">This is an automatically generated email, please do not reply.</td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:10px; text-align: center;\">\r\n" + 
				"                    © Yoyda 2021, All Rights Reserved.\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"  </center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		this.sendEmail(user.get().getEmail(), Subject, body1);

       
      
		
			}
    public void newUserSocialRegisteration(UserModel user) throws MessagingException {
		
		String Subject = "Welcome to Yoyda";
	
		String name= user.getFullName();
		final String token = jwtTokenUtil.generateToken(user);
		String body1="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <title>Yoyda</title>\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width\"/>\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"      body {\r\n" + 
				"        margin: 0;\r\n" + 
				"        mso-line-height-rule: exactly;\r\n" + 
				"        padding: 0;\r\n" + 
				"        min-width: 100%;\r\n" + 
				"      }\r\n" + 
				"      table {\r\n" + 
				"        border-collapse: collapse;\r\n" + 
				"        border-spacing: 0;\r\n" + 
				"      }\r\n" + 
				"      td {\r\n" + 
				"        padding: 0;\r\n" + 
				"        vertical-align: top;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"      .image1 {  font-size: 12px;\r\n" + 
				"        Margin-bottom: 24px;\r\n" + 
				"        mso-line-height-rule: at-least;\r\n" + 
				"      }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body style=\"margin: 0;min-width: 100%;background-color: #fff\">\r\n" + 
				"  <center class=\"wrapper\" style=\"display: table;table-layout: fixed;width: 100%;min-width: 600px;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;background-color: #fff\">\r\n" + 
				"    <table cellpadding=\"0\" cellspacing=\"0\" class=\"header centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"logo\" style=\"padding: 32px 0px; vertical-align: top;\">\r\n" + 
				"            <div class=\"logo-center\" style=\"text-align: center\" align=\"center\" id=\"email-header\"><a href=\"https://www.yoyda.com\" target=\"_blank\">\r\n" + 
				"              <img style=\"border: 0;display: block;Margin-left: auto;Margin-right: auto;\" src=\"https://www.yoyda.com/bazaar/assets/images/logo/logo_bazaar.png\" alt=\"\"></a>\r\n" + 
				"            </div>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"    <table class=\"centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"border\" style=\"padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;width: 1px\">​</td>\r\n" + 
				"          <td style=\"padding: 0;vertical-align: top\">\r\n" + 
				"            <table class=\"one-col\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px;background-color: #ffffff;font-size: 14px;table-layout: fixed\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td class=\"column\" style=\"padding: 0;vertical-align: top;text-align: left\">\r\n" + 
				"                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"contents\" style=\"border-collapse: collapse;border-spacing: 0; border:1px solid #ededed; table-layout: fixed;width: 100%\">\r\n" + 
				"                      <tbody>\r\n" + 
				"                        <tr>\r\n" + 
				"                          <td class=\"padded\" style=\"padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px;word-break: break-word;word-wrap: break-word\">\r\n" + 
				"                            <p style=\"color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 20px; Margin-top: 24px; \">Dear "+name+",</p>\r\n" + 
				"                            <h1 style=\"Margin-top: 0;color: #565656;font-weight: 700;font-size: 26px;Margin-bottom: 18px;font-family: sans-serif;line-height: 32px; text-align: center\">Welcome to your Yoyda!</h1>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 40px;text-align: center\">Simply verify your email address so we know this account belongs to you.</p>\r\n" + 
				"                            <p style=\"text-align:center;\"><a href='"+env.getProperty("app.ui.name")+"/email/verify?_verifytokenviasignature="+token+"' target=\"_blank\" style=\"text-transform:uppercase; font-family: sans-serif;text-decoration:none; background-color:#fcab52; color:#ffffff; display:inline-block; padding:10px 15px; border-radius:3px;\">verify Email Address</a></p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 50px;text-align: center\">\r\n" + 
				"                              If you didn’t initiate this request, you can ignore this email message.\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"padding-top: 50px;color:#565656;font-family: sans-serif;font-size: 16px;line-height: 20px;\">\r\n" + 
				"                              All the best,\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"color:#565656;font-family: sans-serif;font-size: 16px;padding-bottom: 30px;\">\r\n" + 
				"                              <strong>Team Yoyda</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                          </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                      </tbody>\r\n" + 
				"                    </table>\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:20px; text-align: center;\">This is an automatically generated email, please do not reply.</td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:10px; text-align: center;\">\r\n" + 
				"                    © Yoyda 2021, All Rights Reserved.\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"  </center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		this.sendEmail(user.getEmail(), Subject, body1);
		//this.sendEmail("gauravpal7710@gmail.com", Subject, body1);
			}
	
public void updatePassword(UserModel user) throws MessagingException {
		
		String Subject = "Yoyda | Profile Updated";
	
		String name= user.getFullName();
		
		String body1="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <title>Yoyda</title>\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width\"/>\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"      body {\r\n" + 
				"        margin: 0;\r\n" + 
				"        mso-line-height-rule: exactly;\r\n" + 
				"        padding: 0;\r\n" + 
				"        min-width: 100%;\r\n" + 
				"      }\r\n" + 
				"      table {\r\n" + 
				"        border-collapse: collapse;\r\n" + 
				"        border-spacing: 0;\r\n" + 
				"      }\r\n" + 
				"      td {\r\n" + 
				"        padding: 0;\r\n" + 
				"        vertical-align: top;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"      .image1 {  font-size: 12px;\r\n" + 
				"        Margin-bottom: 24px;\r\n" + 
				"        mso-line-height-rule: at-least;\r\n" + 
				"      }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body style=\"margin: 0;min-width: 100%;background-color: #fff\">\r\n" + 
				"  <center class=\"wrapper\" style=\"display: table;table-layout: fixed;width: 100%;min-width: 600px;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;background-color: #fff\">\r\n" + 
				"    <table cellpadding=\"0\" cellspacing=\"0\" class=\"header centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"logo\" style=\"padding: 32px 0px; vertical-align: top;\">\r\n" + 
				"            <div class=\"logo-center\" style=\"text-align: center\" align=\"center\" id=\"email-header\"><a href=\"https://www.yoyda.com\" target=\"_blank\">\r\n" + 
				"              <img style=\"border: 0;display: block;Margin-left: auto;Margin-right: auto;\" src=\"https://www.yoyda.com/bazaar/assets/images/logo/logo_bazaar.png\" alt=\"\"></a>\r\n" + 
				"            </div>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"    <table class=\"centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"border\" style=\"padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;width: 1px\">​</td>\r\n" + 
				"          <td style=\"padding: 0;vertical-align: top\">\r\n" + 
				"            <table class=\"one-col\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px;background-color: #ffffff;font-size: 14px;table-layout: fixed\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td class=\"column\" style=\"padding: 0;vertical-align: top;text-align: left\">\r\n" + 
				"                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"contents\" style=\"border-collapse: collapse;border-spacing: 0; border:1px solid #ededed; table-layout: fixed;width: 100%\">\r\n" + 
				"                      <tbody>\r\n" + 
				"                        <tr>\r\n" + 
				"                          <td class=\"padded\" style=\"padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px;word-break: break-word;word-wrap: break-word\">\r\n" + 
				"                            <p style=\"color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 20px; Margin-top: 24px; \">Dear "+name+",</p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 40px;text-align: center;\">The password for your Yoyda Account was recently changed.</p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;text-align: center\">\r\n" + 
				"                              If you have not made this change, please contact our support immediately.\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"padding-top: 50px;color:#565656;font-family: sans-serif;font-size: 16px;line-height: 20px;\">\r\n" + 
				"                              Thank You\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"color:#565656;font-family: sans-serif;font-size: 16px;padding-bottom: 30px;\">\r\n" + 
				"                              <strong>Team Yoyda</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                          </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                      </tbody>\r\n" + 
				"                    </table>\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:20px; text-align: center;\">This is an automatically generated email, please do not reply.</td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:10px; text-align: center;\">\r\n" + 
				"                    © Yoyda 2021, All Rights Reserved.\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"  </center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		this.sendEmail(user.getEmail(), Subject, body1);
		
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

	public void emailVerify(UserModel user) throws MessagingException {
		String Subject = "Yoyda | Verification";
		
		String name= user.getFullName();
		final String token = jwtTokenUtil.generateToken(user);
		String body1="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <title>Yoyda</title>\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width\"/>\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"      body {\r\n" + 
				"        margin: 0;\r\n" + 
				"        mso-line-height-rule: exactly;\r\n" + 
				"        padding: 0;\r\n" + 
				"        min-width: 100%;\r\n" + 
				"      }\r\n" + 
				"      table {\r\n" + 
				"        border-collapse: collapse;\r\n" + 
				"        border-spacing: 0;\r\n" + 
				"      }\r\n" + 
				"      td {\r\n" + 
				"        padding: 0;\r\n" + 
				"        vertical-align: top;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"      .image1 {  font-size: 12px;\r\n" + 
				"        Margin-bottom: 24px;\r\n" + 
				"        mso-line-height-rule: at-least;\r\n" + 
				"      }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body style=\"margin: 0;min-width: 100%;background-color: #fff\">\r\n" + 
				"  <center class=\"wrapper\" style=\"display: table;table-layout: fixed;width: 100%;min-width: 600px;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;background-color: #fff\">\r\n" + 
				"    <table cellpadding=\"0\" cellspacing=\"0\" class=\"header centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"logo\" style=\"padding: 32px 0px; vertical-align: top;\">\r\n" + 
				"            <div class=\"logo-center\" style=\"text-align: center\" align=\"center\" id=\"email-header\"><a href=\"https://www.yoyda.com\" target=\"_blank\">\r\n" + 
				"              <img style=\"border: 0;display: block;Margin-left: auto;Margin-right: auto;\" src=\"https://www.yoyda.com/bazaar/assets/images/logo/logo_bazaar.png\" alt=\"\"></a>\r\n" + 
				"            </div>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"    <table class=\"centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"border\" style=\"padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;width: 1px\">​</td>\r\n" + 
				"          <td style=\"padding: 0;vertical-align: top\">\r\n" + 
				"            <table class=\"one-col\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px;background-color: #ffffff;font-size: 14px;table-layout: fixed\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td class=\"column\" style=\"padding: 0;vertical-align: top;text-align: left\">\r\n" + 
				"                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"contents\" style=\"border-collapse: collapse;border-spacing: 0; border:1px solid #ededed; table-layout: fixed;width: 100%\">\r\n" + 
				"                      <tbody>\r\n" + 
				"                        <tr>\r\n" + 
				"                          <td class=\"padded\" style=\"padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px;word-break: break-word;word-wrap: break-word\">\r\n" + 
				"                            <p style=\"color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 20px; Margin-top: 24px; \">Dear "+name+",</p>\r\n" + 
				"                            <h1 style=\"Margin-top: 0;color: #565656;font-weight: 700;font-size: 26px;Margin-bottom: 18px;font-family: sans-serif;line-height: 32px; text-align: center\">Welcome to your Yoyda!</h1>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 40px;text-align: center\">Simply verify your email address so we know this account belongs to you.</p>\r\n" + 
				"                            <p style=\"text-align:center;\"><a href='"+env.getProperty("app.ui.name")+"/email/verify?_verifytokenviasignature="+token+"' target=\"_blank\" style=\"text-transform:uppercase; font-family: sans-serif;text-decoration:none; background-color:#fcab52; color:#ffffff; display:inline-block; padding:10px 15px; border-radius:3px;\">verify Email Address</a></p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 50px;text-align: center\">\r\n" + 
				"                              If you didn’t initiate this request, you can ignore this email message.\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"padding-top: 50px;color:#565656;font-family: sans-serif;font-size: 16px;line-height: 20px;\">\r\n" + 
				"                              All the best,\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"color:#565656;font-family: sans-serif;font-size: 16px;padding-bottom: 30px;\">\r\n" + 
				"                              <strong>Team Yoyda</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                          </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                      </tbody>\r\n" + 
				"                    </table>\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:20px; text-align: center;\">This is an automatically generated email, please do not reply.</td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:10px; text-align: center;\">\r\n" + 
				"                    © Yoyda 2021, All Rights Reserved.\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"  </center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		this.sendEmail(user.getEmail(), Subject, body1);
	}

	public void phoneVerify(Optional<UserModel> user) throws MessagingException {
		
		String Subject = "Email Verification | Bazaar Classsified | Yupex";
	
		String name= user.get().getFullName();
		final String token = jwtTokenUtil.generateToken(user.get());
		String body1="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <title>Yoyda</title>\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width\"/>\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"      body {\r\n" + 
				"        margin: 0;\r\n" + 
				"        mso-line-height-rule: exactly;\r\n" + 
				"        padding: 0;\r\n" + 
				"        min-width: 100%;\r\n" + 
				"      }\r\n" + 
				"      table {\r\n" + 
				"        border-collapse: collapse;\r\n" + 
				"        border-spacing: 0;\r\n" + 
				"      }\r\n" + 
				"      td {\r\n" + 
				"        padding: 0;\r\n" + 
				"        vertical-align: top;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"      .image1 {  font-size: 12px;\r\n" + 
				"        Margin-bottom: 24px;\r\n" + 
				"        mso-line-height-rule: at-least;\r\n" + 
				"      }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body style=\"margin: 0;min-width: 100%;background-color: #fff\">\r\n" + 
				"  <center class=\"wrapper\" style=\"display: table;table-layout: fixed;width: 100%;min-width: 600px;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;background-color: #fff\">\r\n" + 
				"    <table cellpadding=\"0\" cellspacing=\"0\" class=\"header centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"logo\" style=\"padding: 32px 0px; vertical-align: top;\">\r\n" + 
				"            <div class=\"logo-center\" style=\"text-align: center\" align=\"center\" id=\"email-header\"><a href=\"https://www.yoyda.com\" target=\"_blank\">\r\n" + 
				"              <img style=\"border: 0;display: block;Margin-left: auto;Margin-right: auto;\" src=\"https://www.yoyda.com/bazaar/assets/images/logo/logo_bazaar.png\" alt=\"\"></a>\r\n" + 
				"            </div>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"    <table class=\"centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"border\" style=\"padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;width: 1px\">​</td>\r\n" + 
				"          <td style=\"padding: 0;vertical-align: top\">\r\n" + 
				"            <table class=\"one-col\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px;background-color: #ffffff;font-size: 14px;table-layout: fixed\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td class=\"column\" style=\"padding: 0;vertical-align: top;text-align: left\">\r\n" + 
				"                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"contents\" style=\"border-collapse: collapse;border-spacing: 0; border:1px solid #ededed; table-layout: fixed;width: 100%\">\r\n" + 
				"                      <tbody>\r\n" + 
				"                        <tr>\r\n" + 
				"                          <td class=\"padded\" style=\"padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px;word-break: break-word;word-wrap: break-word\">\r\n" + 
				"                            <p style=\"color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 20px; Margin-top: 24px; \">Dear "+name+",</p>\r\n" + 
				"                            <h1 style=\"Margin-top: 0;color: #565656;font-weight: 700;font-size: 26px;Margin-bottom: 18px;font-family: sans-serif;line-height: 32px; text-align: center\">Welcome to your Yoyda!</h1>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 40px;text-align: center\">Simply verify your email address so we know this account belongs to you.</p>\r\n" + 
				"                            <p style=\"text-align:center;\"><a href='"+env.getProperty("app.ui.name")+"/email/verify?_verifytokenviasignature="+token+"' target=\"_blank\" style=\"text-transform:uppercase; font-family: sans-serif;text-decoration:none; background-color:#fcab52; color:#ffffff; display:inline-block; padding:10px 15px; border-radius:3px;\">verify Email Address</a></p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;padding-top: 50px;text-align: center\">\r\n" + 
				"                              If you didn’t initiate this request, you can ignore this email message.\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"padding-top: 50px;color:#565656;font-family: sans-serif;font-size: 16px;line-height: 20px;\">\r\n" + 
				"                              All the best,\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"color:#565656;font-family: sans-serif;font-size: 16px;padding-bottom: 30px;\">\r\n" + 
				"                              <strong>Team Yoyda</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                          </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                      </tbody>\r\n" + 
				"                    </table>\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:20px; text-align: center;\">This is an automatically generated email, please do not reply.</td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:10px; text-align: center;\">\r\n" + 
				"                    © Yoyda 2021, All Rights Reserved.\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"  </center>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		this.sendEmail(user.get().getEmail(), Subject, body1);
	}
	
public void userForgotPin(UserModel user) throws MessagingException{
		String name = user.getFullName();
		String pin=getAlphaNumericString(6);
		  Date dNow = new Date( );
	      SimpleDateFormat ft = new SimpleDateFormat ("dd-MMM-yyyy");
	      String changeDate =ft.format(dNow);
		String Subject = "Yoyda | Forgot password";
		String body = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"    <title>Yoyda</title>\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width\"/>\r\n" + 
				"    <style type=\"text/css\">\r\n" + 
				"      body {\r\n" + 
				"        margin: 0;\r\n" + 
				"        mso-line-height-rule: exactly;\r\n" + 
				"        padding: 0;\r\n" + 
				"        min-width: 100%;\r\n" + 
				"      }\r\n" + 
				"      table {\r\n" + 
				"        border-collapse: collapse;\r\n" + 
				"        border-spacing: 0;\r\n" + 
				"      }\r\n" + 
				"      td {\r\n" + 
				"        padding: 0;\r\n" + 
				"        vertical-align: top;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"      .image1 {  font-size: 12px;\r\n" + 
				"        Margin-bottom: 24px;\r\n" + 
				"        mso-line-height-rule: at-least;\r\n" + 
				"      }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body style=\"margin: 0;min-width: 100%;background-color: #fff\">\r\n" + 
				"  <center class=\"wrapper\" style=\"display: table;table-layout: fixed;width: 100%;min-width: 600px;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;background-color: #fff\">\r\n" + 
				"    <table cellpadding=\"0\" cellspacing=\"0\" class=\"header centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"logo\" style=\"padding: 32px 0px; vertical-align: top;\">\r\n" + 
				"            <div class=\"logo-center\" style=\"text-align: center\" align=\"center\" id=\"email-header\"><a href=\"https://www.yoyda.com\" target=\"_blank\">\r\n" + 
				"              <img style=\"border: 0;display: block;Margin-left: auto;Margin-right: auto;\" src=\"https://www.yoyda.com/bazaar/assets/images/logo/logo_bazaar.png\" alt=\"\"></a>\r\n" + 
				"            </div>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"    <table class=\"centered\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto\">\r\n" + 
				"      <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"          <td class=\"border\" style=\"padding: 0;vertical-align: top;font-size: 1px;line-height: 1px;width: 1px\">​</td>\r\n" + 
				"          <td style=\"padding: 0;vertical-align: top\">\r\n" + 
				"            <table class=\"one-col\" style=\"border-collapse: collapse;border-spacing: 0;Margin-left: auto;Margin-right: auto;width: 600px;background-color: #ffffff;font-size: 14px;table-layout: fixed\">\r\n" + 
				"              <tbody>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td class=\"column\" style=\"padding: 0;vertical-align: top;text-align: left\">\r\n" + 
				"                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"contents\" style=\"border-collapse: collapse;border-spacing: 0; border:1px solid #ededed; table-layout: fixed;width: 100%\">\r\n" + 
				"                      <tbody>\r\n" + 
				"                        <tr>\r\n" + 
				"                          <td class=\"padded\" style=\"padding: 0;vertical-align: top;padding-left: 32px;padding-right: 32px;word-break: break-word;word-wrap: break-word\">\r\n" + 
				"                            <p style=\"color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 24px; Margin-top: 24px; \">Dear "+name+",</p>\r\n" + 
				"                            <h1 style=\"Margin-top: 0;color: #565656;font-weight: 700;font-size: 26px;Margin-bottom: 18px;font-family: sans-serif;line-height: 32px; text-align: center\">We have received your request to reset your password</h1>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 24px;text-align: center\">on "+changeDate+"</p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 34px;text-align: center\">\r\n" + 
				"                              Your new password is: <strong style=\"text-transform:uppercase;\">"+pin.toUpperCase()+"</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"padding-top: 30px;color:#565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 15px;text-align: center\">\r\n" + 
				"                              <strong style=\"font-weight: bold\">Why do I have to reset my password?</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"Margin-top: 0;color: #565656;font-family: sans-serif;font-size: 16px;line-height: 24px;Margin-bottom: 24px;text-align: center\">\r\n" + 
				"                              As a security best practice, we not store your password. As such, we cannot simply send you your old password. A unique password has been generated for you.\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"padding-top: 50px;color:#565656;font-family: sans-serif;font-size: 16px;line-height: 20px;\">\r\n" + 
				"                              Thank You\r\n" + 
				"                            </p>\r\n" + 
				"                            <p style=\"color:#565656;font-family: sans-serif;font-size: 16px;padding-bottom: 30px;\">\r\n" + 
				"                              <strong>Team Yoyda</strong>\r\n" + 
				"                            </p>\r\n" + 
				"                          </td>\r\n" + 
				"                        </tr>\r\n" + 
				"                      </tbody>\r\n" + 
				"                    </table>\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:20px; text-align: center;\">This is an automatically generated email, please do not reply.</td>\r\n" + 
				"                </tr>\r\n" + 
				"                <tr>\r\n" + 
				"                  <td style=\"font-family: sans-serif;font-size: 11px; color:#565656; padding-top:10px; text-align: center;\">\r\n" + 
				"                    © Yoyda 2021, All Rights Reserved.\r\n" + 
				"                  </td>\r\n" + 
				"                </tr>\r\n" + 
				"              </tbody>\r\n" + 
				"            </table>\r\n" + 
				"          </td>\r\n" + 
				"        </tr>\r\n" + 
				"      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"  </center>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		user.setPassword(pin.toUpperCase());
		UserModel savedUser = userDetailsService.save(user);
		System.out.println("Link"+savedUser.toString());
		this.sendEmail(user.getEmail(), Subject, body);
		
	}
	
	
public static String getAlphaNumericString(int n) 
{ 
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                + "0123456789"
                                + "abcdefghijklmnopqrstuvxyz"; 

    // create StringBuffer size of AlphaNumericString 
    StringBuilder sb = new StringBuilder(n); 

    for (int i = 0; i < n; i++) { 
        int index 
            = (int)(AlphaNumericString.length() 
                    * Math.random()); 
        sb.append(AlphaNumericString 
                      .charAt(index)); 
    } 

    return sb.toString(); 
} 

	
}
