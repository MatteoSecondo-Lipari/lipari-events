package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;

public interface LocationService {
	
	public List<LocationWithEventsDTO> getAllLocation();
	
	public List<LocationSeatsDTO> getAllSeats(); 
	
	public List<LocationSeatsDTO> getAvailableSeatsForEvent(long eventId);
}