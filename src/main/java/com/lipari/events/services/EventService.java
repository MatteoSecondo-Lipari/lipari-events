package com.lipari.events.services;

import java.util.List;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.EventWithSubcategoryWithoutloopDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;

public interface EventService {

	public EventWithSubcategoryWithoutloopDTO createOrUpdateEvent(EventConstraintsDTO event, String imagePath);
	
	public List<EventWithSubcategoryWithoutloopDTO> getAllEvents();
	public EventDTO getEventDTOById(long id);
	public EventWithSubcategoryWithoutloopDTO getEventWithSubcategoryWithoutloopDTOById(long id);
	public EventEntity getEventEntityById(long id);
	
	public boolean deleteEvent(long id);

	public boolean isPresentEntertainersStripeAccount(List<EntertainerDTO> entertainers);
	
	public List<EventWithSubcategoryWithoutloopDTO> getEventWithName(String name);
	
	public List<EventWithSubcategoryWithoutloopDTO> getTop20newestEvents();
	
}
