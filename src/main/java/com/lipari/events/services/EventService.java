package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EventDTO;
import com.lipari.events.models.EventTicketDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;

public interface EventService {

	public EventDTO createOrUpdateEvent(EventConstraintsDTO event, String imagePath, String authorization);
	
	public List<EventDTO> getAllEvents();
	public EventDTO getEventById(long id);
	public List<EventTicketDTO> getAllEventTicket();
}
