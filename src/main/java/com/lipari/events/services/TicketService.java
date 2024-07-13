package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.TicketDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface TicketService {

	public long getTicketPrice(long id);
	
	public Session purchase(List<TicketDTO> tickets, long price) throws StripeException;
}
