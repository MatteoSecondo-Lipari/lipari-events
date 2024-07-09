package com.lipari.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.constraints.EventConstraintsDTO;
import com.lipari.events.services.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	EventService eventService;

	//TODO: Bisogna ricevere nella richiesta anche una immagine di copertina
	@PreAuthorize("hasAnyRole('ROLE_ENTERTAINER','ROLE_ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<?> createOrUpdate(@RequestBody @Valid EventConstraintsDTO event) {
		return ResponseEntity.ok(eventService.createOrUpdateEvent(event));
	}
	
}
