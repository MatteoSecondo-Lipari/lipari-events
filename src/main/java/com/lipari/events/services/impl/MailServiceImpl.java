package com.lipari.events.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.MailEntity;
import com.lipari.events.services.MailService;

@Service
public class MailServiceImpl implements MailService{

	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("$(spring.mail.username)")
	private String fromMail;
	
	@Override
	public void sendMail(String mail, MailEntity message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromMail);
		simpleMailMessage.setSubject(message.getSubject());
		simpleMailMessage.setText(message.getMessage());
		simpleMailMessage.setTo(mail);
		
		mailSender.send(simpleMailMessage);
		
	}
	
	
}