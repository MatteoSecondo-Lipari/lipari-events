package com.lipari.events.services;

import java.util.List;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.models.TicketDTO;

public interface StripeRequestsStorageService {

	public void addTicketsToQueue(String key, List<TicketDTO> tickets);
	public List<TicketDTO> getTicketsFromQueue(String key);
	public void clearQueue(String key);
	
	public void addEntertainer(String key, EntertainerEntity entertainer);
	public EntertainerEntity getEntertainer(String key);
	public void removeEntertainer(String key);
	
	

}
