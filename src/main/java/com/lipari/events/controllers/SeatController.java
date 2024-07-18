package com.lipari.events.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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


import com.lipari.events.entities.SeatEntity;

import com.lipari.events.models.SeatDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.SeatService;

@RestController
@RequestMapping("/seats")
public class SeatController {
	
	@Autowired
	SeatService seatService;
	
	 
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/all")
	public List<SeatDTO> getAll() {
		return seatService.getAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/create-update")
	public SeatDTO CreateOrUpdate(@RequestBody	SeatEntity seats) {
		return seatService.createOrUpdate(seats);
	}
	
	// READ
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(seatService.getById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(new MessageResponse("No seat found", 404));
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return seatService.delete(id);
	}
	
	
}
