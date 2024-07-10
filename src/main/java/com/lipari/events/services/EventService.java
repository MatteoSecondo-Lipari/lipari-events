package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EventDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;

public interface EventService {

	public EventDTO createOrUpdateEvent(EventConstraintsDTO event, String imagePath);
	
	public List<EventDTO> getAllEvents();
	public EventDTO getEventById(long id);
	
}
