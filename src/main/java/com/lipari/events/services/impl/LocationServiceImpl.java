package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.mappers.LocationMapper;
import com.lipari.events.models.LocationWithEventsDTO;
import com.lipari.events.repositories.LocationRepository;
import com.lipari.events.services.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	LocationMapper locationMapper;
	
	@Override
	public List<LocationWithEventsDTO> getAllLocation() {
		return locationRepository.findAll().stream()
				.map(locationMapper::entityToLocationWithEventsDTO).toList();
	}
	
}