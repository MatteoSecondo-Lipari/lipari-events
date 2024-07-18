package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.entities.LocationEntity;
import com.lipari.events.entities.SeatEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.mappers.EventMapper;
import com.lipari.events.mappers.SeatMapper;
import com.lipari.events.models.EntertainerDTO;

import com.lipari.events.models.EventDTO;

import com.lipari.events.models.EventWithSubcategoryWithoutloopDTO;
import com.lipari.events.models.SeatDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;
import com.lipari.events.repositories.EntertainerRepository;
import com.lipari.events.repositories.EventRepository;
import com.lipari.events.repositories.SeatRepository;
import com.lipari.events.repositories.UserRepository;
import com.lipari.events.services.EventService;
import com.lipari.events.services.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	SeatMapper seatMapper;
	
	@Override
	public List<SeatDTO> getAll() {
		return seatRepository.findAll().stream().map(seatMapper::entityToDto).toList();
	}

	@Override
	public SeatDTO createOrUpdate(SeatEntity seat) {
		return seatMapper.entityToDto(seatRepository.save(seat));		
	}

	@Override
	public SeatDTO getById(int id) {
		return seatRepository.findById(id).map(seatMapper::entityToDto).orElseThrow();
				
	}

	@Override
	public boolean delete(int id) {
		SeatEntity seat = seatRepository.findById(id).orElseThrow();
		if(seat != null)
		{
			seatRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	

}


