package com.yupexx.bazaar.api.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

public class EmailBaseService {

	@Autowired
    private JavaMailSender javaMailSender;

	@Async
	void sendEmail(String to,String Subject,String body) throws MessagingException {

		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true,"UTF-8");
		helper.setTo(to);
		helper.setSubject(Subject);
		helper.setText(body,true); // HTML

        javaMailSender.send(msg);

    }
	
	void sendEmailWithAttachment(String to,String Subject,String body) throws MessagingException {

		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(to);
		helper.setSubject(Subject);
		helper.setText(body,true); // HTML
        javaMailSender.send(msg);

    }
}
