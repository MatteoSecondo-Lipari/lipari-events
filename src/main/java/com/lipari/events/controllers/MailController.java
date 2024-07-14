package com.lipari.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.entities.MailEntity;
import com.lipari.events.services.MailService;

@RestController
@RequestMapping("/email")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/send/{Receiveremail}")
	public String sendMail(@PathVariable String Receiveremail, @RequestBody MailEntity message) {
	mailService.sendMail(Receiveremail, message);
	return "Successfully sent the email";
	}
	
	
}