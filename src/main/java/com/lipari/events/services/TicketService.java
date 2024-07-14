package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.TicketDTO;
import com.stripe.exception.StripeException;

public interface TicketService {

	public boolean saveAll(List<TicketDTO> tickets);
	
	public String checkout(List<TicketDTO> tickets, long price, String transferGroup) throws StripeException;
	public boolean transfers(String transferGroup, long amount) throws StripeException;
	
}
