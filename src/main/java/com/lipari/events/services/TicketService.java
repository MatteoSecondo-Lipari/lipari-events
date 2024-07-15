package com.lipari.events.services;

import java.util.List;

import com.lipari.events.entities.EventsEntertainersEntity;
import com.lipari.events.models.TicketDTO;
import com.stripe.exception.StripeException;

public interface TicketService {

	public boolean saveAll(List<TicketDTO> tickets);
	
	public String checkout(List<TicketDTO> tickets, long price, String transferGroup) throws StripeException;
	public void transfers(List<EventsEntertainersEntity> entertainers, String transferGroup, long totalAmount) throws StripeException;
	
}
