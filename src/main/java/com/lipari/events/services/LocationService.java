package com.lipari.events.services;

import java.util.List;
import java.util.Optional;

import com.lipari.events.entities.LocationEntity;
import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;

public interface LocationService {
	
	public List<LocationWithEventsDTO> getAllLocation();
	
	public List<LocationSeatsDTO> getAllSeats(); 
	
	public List<LocationSeatsDTO> getAvailableSeatsForEvent(long eventId);
	
	public LocationWithEventsDTO createOrUpdate(LocationEntity location);
	public LocationWithEventsDTO getById(int id);
	public boolean delete(int id);
	
}