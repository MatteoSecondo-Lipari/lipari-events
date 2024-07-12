package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.LocationDTO;
import com.lipari.events.services.LocationService;


@RestController
@RequestMapping("/location")
public class LocationController{
	
	@Autowired
	LocationService locationService;
	
	@GetMapping("/all")
	public List<LocationDTO> getAllLocation(LocationDTO l){
		return locationService.getAllLocation();
	}
	
}

