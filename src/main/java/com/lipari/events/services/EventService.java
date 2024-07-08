package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EventDTO;

public interface EventService {

	public EventDTO createOrUpdateEvent(EventDTO event);
	
	public List<EventDTO> getAllEvents();
	public EventDTO getEventById(long id);
	
}
