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
import com.lipari.events.models.EventSubcategoryDTOwithCategoryidDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.EventSubcategoryService;

@RestController
@RequestMapping("/event/category/subcategory")
public class EventSubcategoryController {
	
	@Autowired
	EventSubcategoryService eventSubcategoryService;
	
	

	@GetMapping("/all")
	public List<EventSubcategoryDTOwithCategoryidDTO> getMethodName(EventSubcategoryDTOwithCategoryidDTO category) {
		return eventSubcategoryService.getAll();
	}
	
	// CREATE 
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/create-update")
	public EventSubcategoryDTOwithCategoryidDTO create(@RequestBody	EventSubcategoryDTOwithCategoryidDTO events) {
		return eventSubcategoryService.createOrUpdate(events);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(eventSubcategoryService.getById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(new MessageResponse("No subcategory found", 404));
		}
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return eventSubcategoryService.delete(id);
	}
	
	
}
