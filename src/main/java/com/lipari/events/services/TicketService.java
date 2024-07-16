package com.lipari.events.services;

import java.util.List;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.TicketOrdersDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;

public interface TicketService {
	
	public List<TicketOrdersDTO> getAllByCustomerId(CustomerEntity customer);
	
	public List<TicketsEmptySeatDTO> getAllTicketByEventId(long eventid);
	
}