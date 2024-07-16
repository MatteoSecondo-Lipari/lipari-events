package com.lipari.events.services;

import java.util.List;

import com.lipari.events.entities.EventsEntertainersEntity;
import com.lipari.events.models.TicketDTO;
import com.stripe.exception.StripeException;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.models.TicketOrdersDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;

public interface TicketService {

	public boolean saveAll(List<TicketDTO> tickets);
	
	public String checkout(List<TicketDTO> tickets, long price, String transferGroup) throws StripeException;
	public void transfers(List<EventsEntertainersEntity> entertainers, String transferGroup, long totalAmount) throws StripeException;
	
	public int countTicketsByEventId(long id);
	public int countNumberedTicketsByEventId(long id);
  
	public List<TicketOrdersDTO> getAllByCustomerId(CustomerEntity customer);
	public List<TicketsEmptySeatDTO> getAllTicketByEventId(long eventid);
}

