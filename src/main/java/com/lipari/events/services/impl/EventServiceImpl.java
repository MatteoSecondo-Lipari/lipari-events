package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.mappers.EventMapper;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;
import com.lipari.events.repositories.EntertainerRepository;
import com.lipari.events.repositories.EventRepository;
import com.lipari.events.services.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventMapper eventMapper;
	
	@Autowired
	EntertainerRepository entertainerRepository;
	
	@Override
	public EventDTO createEvent(EventConstraintsDTO event, String imagePath) {
		EventEntity ee = eventMapper.constraintsDtoToEntity(event);

		if(imagePath != null) {
			ee.setImagePath(imagePath);
		}

		return eventMapper.entityToDto(eventRepository.save(ee));
	}

	@Override
	public List<EventDTO> getAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventDTO getEventDTOById(long id) {
		return eventMapper.entityToDto(eventRepository.findById(id).orElseThrow());
	}

	@Override
	public EventEntity getEventEntityById(long id) {
		return eventRepository.findById(id).orElse(null);
	}
	
	@Override
	public boolean isPresentEntertainersStripeAccount(List<EntertainerDTO> entertainers) {
		
		for (EntertainerDTO e : entertainers) {
			 if(entertainerRepository.findById(e.getId()).orElseThrow().getStripeConnectedAccount() == null) {
				 return false;
			 }
		}
		
		return true;
	}
}
