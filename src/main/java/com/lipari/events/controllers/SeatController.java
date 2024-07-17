package com.lipari.events.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.lipari.events.entities.SeatEntity;

import com.lipari.events.models.SeatDTO;

import com.lipari.events.services.SeatService;

@RestController
@RequestMapping("/seats")
public class SeatController {
	
	@Autowired
	SeatService seatService;
	
	 

	@GetMapping("/all")
	public List<SeatDTO> getAll() {
		return seatService.getAll();
	}
	
	// CREATE 
	@GetMapping("/create-update")
	public SeatDTO CreateOrUpdate(@RequestBody	SeatEntity seats) {
		return seatService.createOrUpdate(seats);
	}
	
	// READ
	@GetMapping("/get/{id}")
	public SeatDTO getById(@PathVariable int id) {
		return seatService.getById(id);
	}
	
	// DELETE
	@GetMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return seatService.delete(id);
	}
	
	
}
