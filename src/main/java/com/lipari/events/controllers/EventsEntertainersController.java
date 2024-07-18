package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.EventsEntertainersDTO;
import com.lipari.events.models.constraints.EventsEntertainersConstraintsDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.EventsEntertainersService;


@RestController
@RequestMapping("/events-entertainers")
public class EventsEntertainersController {
	
	@Autowired
	EventsEntertainersService eventsEntertainersService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/create")
	public EventsEntertainersDTO createOrUpdate(@RequestBody EventsEntertainersConstraintsDTO ee) {
		return eventsEntertainersService.createOrUpdate(ee);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/all")
	public List<EventsEntertainersDTO> getAll() {
		return eventsEntertainersService.getAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public EventsEntertainersDTO getById(@PathVariable long id) {
		return eventsEntertainersService.getById(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		
		if(!eventsEntertainersService.delete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Entry not existing", 404));
		}
		
		return ResponseEntity.ok().body(
				new MessageResponse("Entry deleted successfully", 200));
	}
	
}
