package com.lipari.events.services;

import com.lipari.events.models.EventsEntertainersDTO;
import com.lipari.events.models.constraints.EventsEntertainersConstraintsDTO;

public interface EventsEntertainersService {

	public EventsEntertainersDTO create(EventsEntertainersConstraintsDTO eventsEntertainers);
}
