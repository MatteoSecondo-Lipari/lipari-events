package com.lipari.events.services;



import com.lipari.events.entities.MailEntity;


public interface MailService {

	public void sendMail(String mail, MailEntity message);

}
