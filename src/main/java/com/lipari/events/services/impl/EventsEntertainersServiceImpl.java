package com.lipari.events.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventsEntertainersEntity;
import com.lipari.events.mappers.EventsEntertainersMapper;
import com.lipari.events.models.EventsEntertainersDTO;
import com.lipari.events.models.constraints.EventsEntertainersConstraintsDTO;
import com.lipari.events.repositories.EventsEntertainersRepository;
import com.lipari.events.services.EventsEntertainersService;

@Service
public class EventsEntertainersServiceImpl implements EventsEntertainersService {
	
	@Autowired
	EventsEntertainersRepository eventsEntertainersRepository;
	
	@Autowired
	EventsEntertainersMapper eventsEntertainersMapper;

	@Override
	public EventsEntertainersDTO create(EventsEntertainersConstraintsDTO eventsEntertainers) {
		EventsEntertainersEntity ee = eventsEntertainersMapper.constraintsDtoToEntity(eventsEntertainers);
		return eventsEntertainersMapper.entityToDto(eventsEntertainersRepository.save(ee));
	}

}
