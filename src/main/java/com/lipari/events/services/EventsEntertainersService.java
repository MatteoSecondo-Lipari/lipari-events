package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EventsEntertainersDTO;
import com.lipari.events.models.constraints.EventsEntertainersConstraintsDTO;

public interface EventsEntertainersService {

	public EventsEntertainersDTO createOrUpdate(EventsEntertainersConstraintsDTO eventsEntertainers);
	
	public List<EventsEntertainersDTO> getAll();
	public EventsEntertainersDTO getById(long id);
	
	public boolean delete(long id);
}
