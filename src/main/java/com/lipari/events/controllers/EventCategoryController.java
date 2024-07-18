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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.entities.EventCategoryEntity;
import com.lipari.events.models.EventCategoryDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.EventCategoryService;

@RestController
@RequestMapping("/event/category")
public class EventCategoryController {
	
	@Autowired
	EventCategoryService categoryService;

	//get all events for each category
	@GetMapping("/all-events")
	public List<EventCategoryDTO> getMethodName(EventCategoryDTO category) {
		return categoryService.getAll();
	}
	
	// CREATE 
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/create")
	public EventCategoryDTO create(@RequestBody	EventCategoryEntity events) {
		return categoryService.create(events);
	}
	
	// READ
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(categoryService.getById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(new MessageResponse("No category found", 404));
		}
		
	}
	
	// UPDATE 
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public EventCategoryDTO update(@RequestBody EventCategoryEntity events) {
		return categoryService.update(events);
	}
	
	// DELETE
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return categoryService.delete(id);
	}
	
}
