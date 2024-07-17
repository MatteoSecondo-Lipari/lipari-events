package com.lipari.events.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.entities.LocationEntity;
import com.lipari.events.models.LocationDTO;
import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;
import com.lipari.events.services.LocationService;


@RestController
@RequestMapping("/location")
public class LocationController{
	
	@Autowired
	LocationService locationService;
	
	@GetMapping("/all")
	public List<LocationWithEventsDTO> getAllLocation(LocationDTO l){
		return locationService.getAllLocation();
	}
	
	@GetMapping("/seats/{event}")
	public List<LocationSeatsDTO> getSeats(@PathVariable long event){
		return locationService.getAvailableSeatsForEvent(event);
	}
	
	
	@GetMapping("/create-update")
	public LocationDTO create(@RequestBody	LocationEntity events) {
		return locationService.createOrUpdate(events);
	}
	
	// READ
	@GetMapping("/get/{id}")
	public LocationDTO getById(@PathVariable int id) {
		return locationService.getById(id);
	}
	
	// DELETE
	@GetMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return locationService.delete(id);
	}
}

