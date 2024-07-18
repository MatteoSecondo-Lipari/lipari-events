package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.mappers.EventMapper;
import com.lipari.events.models.EntertainerDTO;

import com.lipari.events.models.EventDTO;

import com.lipari.events.models.EventWithSubcategoryWithoutloopDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;
import com.lipari.events.repositories.EntertainerRepository;
import com.lipari.events.repositories.EventRepository;
import com.lipari.events.repositories.UserRepository;
import com.lipari.events.services.EventService;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventMapper eventMapper;
	
	@Autowired
	EntertainerRepository entertainerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntertainerMapper entertainerMapper;

	@Override
	public EventDTO createOrUpdateEvent(EventConstraintsDTO event, String imagePath) {
		EventEntity ee = eventMapper.constraintsDtoToEntity(event);

		if(imagePath != null) {
			ee.setImagePath(imagePath);
		}

		return eventMapper.entityToDto(eventRepository.save(ee));
	}

	@Override
	public List<EventWithSubcategoryWithoutloopDTO> getAllEvents() {
		return eventRepository.findAll()
				.stream().map(eventMapper::entityToEventWithSubcategoryWithoutLoopToDto).toList();
	}

	@Override
	public EventDTO getEventDTOById(long id) {
		return eventMapper.entityToDto(eventRepository.findById(id).orElseThrow());
	}
	
	@Override
	public EventWithSubcategoryWithoutloopDTO getEventWithSubcategoryWithoutloopDTOById(long id) {
		return eventMapper.entityToEventWithSubcategoryWithoutLoopToDto(eventRepository.findById(id).orElse(null));
	}
	
	@Override
	public List<EventWithSubcategoryWithoutloopDTO> getEventWithName(String name) {
		return eventRepository.findEventByNameStartingWith(name).stream()
				.map(eventMapper::entityToEventWithSubcategoryWithoutLoopToDto).toList();
	}
	

	@Override
	public List<EventWithSubcategoryWithoutloopDTO> getTop20newestEvents(){
		return eventRepository.findTop20ByDateOrderDesc().stream()
				.map(eventMapper::entityToEventWithSubcategoryWithoutLoopToDto).toList();
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

	@Override
	public boolean deleteEvent(long id) {
		
		if(!eventRepository.existsById(id)) {
			return false;
		}
		
		eventRepository.deleteById(id);
		return true;
	}

}


