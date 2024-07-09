package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.mappers.EventMapper;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;
import com.lipari.events.repositories.EventRepository;
import com.lipari.events.services.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventMapper eventMapper;

	@Override
	public EventDTO createOrUpdateEvent(EventConstraintsDTO event) {
		EventEntity ee = eventMapper.constraintsDtoToEntity(event);
		return eventMapper.entityToDto(eventRepository.save(ee));
	}

	@Override
	public List<EventDTO> getAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventDTO getEventById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
