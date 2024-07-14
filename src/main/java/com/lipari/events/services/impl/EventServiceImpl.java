package com.lipari.events.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.entities.EventEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.mappers.EventMapper;
import com.lipari.events.models.EntertainerDTO;

import com.lipari.events.models.EventDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
import com.lipari.events.models.EventWithSubcategoryWithoutloopDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;
import com.lipari.events.repositories.EntertainerRepository;
import com.lipari.events.repositories.EventRepository;
import com.lipari.events.repositories.UserRepository;
import com.lipari.events.security.jwt.JwtUtils;
import com.lipari.events.services.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventMapper eventMapper;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntertainerMapper entertainerMapper;


	@Override
	public EventDTO createOrUpdateEvent(EventConstraintsDTO event, String imagePath, String authorization) {
		String email = jwtUtils.getEmailFromJwtToken(authorization.substring(7));
		EntertainerEntity entertainerE = userRepository.findByEmail(email).get().getEntertainer();
		EntertainerDTO entertainer = entertainerMapper.entityToDto(entertainerE); 
		
		if(event.getEntertainers() == null) {
			event.setEntertainers(new ArrayList<EntertainerDTO>());
		}
		
		event.getEntertainers().add(entertainer);
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
	public EventDTO getEventById(long id) {
		return eventMapper.entityToDto(eventRepository.findById(id).orElseThrow());
	}
	
	public List<EventWithSubcategoryWithoutloopDTO> getEventWithName(String name) {
		return eventRepository.findEventByNameStartingWith(name).stream()
				.map(eventMapper::EntitySearchWithoutLooptoDto).toList();
	}
	
	 public List<EventStatsDashboardDTO> getEventStatistics() {
	        return eventRepository.getEventStatistics();
	    }
	

}
