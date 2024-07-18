package com.lipari.events.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.entities.LocationEntity;
import com.lipari.events.models.LocationDTO;
import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;
import com.lipari.events.payload.MessageResponse;
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
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/create-update")
	public LocationDTO create(@RequestBody	LocationEntity events) {
		return locationService.createOrUpdate(events);
	}
	
	// READ
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(locationService.getById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(new MessageResponse("No locations found", 404));
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return locationService.delete(id);
	}
}

