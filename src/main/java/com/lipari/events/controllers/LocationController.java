package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.LocationDTO;
import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;
import com.lipari.events.services.LocationService;


@RestController
@RequestMapping("/location")
public class LocationController{
	
	@Autowired
	LocationService locationService;
	
	//get all events for each location
	@GetMapping("/all")
	public List<LocationWithEventsDTO> getAllLocation(LocationDTO l){
		return locationService.getAllLocation();
	}
	
	//get all available seats for an event
	@GetMapping("/seats/{event}")
	public List<LocationSeatsDTO> getSeats(@PathVariable long event){
		return locationService.getAvailableSeatsForEvent(event);
	}
}

