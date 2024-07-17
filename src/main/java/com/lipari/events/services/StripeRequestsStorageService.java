package com.lipari.events.services;

import java.util.List;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.models.constraints.TicketConstraintsDTO;

public interface StripeRequestsStorageService {

	public void addTicketsToQueue(String key, List<TicketConstraintsDTO> tickets);
	public List<TicketConstraintsDTO> getTicketsFromQueue(String key);
	public void clearQueue(String key);
	
	public void addEntertainer(String key, EntertainerEntity entertainer);
	public EntertainerEntity getEntertainer(String key);
	public void removeEntertainer(String key);
	
	

}
