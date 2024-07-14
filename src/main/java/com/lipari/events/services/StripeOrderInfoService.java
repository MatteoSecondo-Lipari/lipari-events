package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.TicketDTO;

public interface StripeOrderInfoService {

	public void addTicketsToQueue(String key, List<TicketDTO> tickets);
	public List<TicketDTO> getTicketsFromQueue(String key);
	public void clearQueue(String key);

}
