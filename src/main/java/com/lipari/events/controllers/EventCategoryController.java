package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.entities.EventCategoryEntity;
import com.lipari.events.models.EventCategoryDTO;
import com.lipari.events.services.EventCategoryService;

@RestController
@RequestMapping("/event/category")
public class EventCategoryController {
	
	@Autowired
	EventCategoryService categoryService;

	@GetMapping("/all-events")
	public List<EventCategoryDTO> getMethodName(EventCategoryDTO category) {
		return categoryService.getAll();
	}
	
	@GetMapping("/create")
	public EventCategoryDTO create(@RequestBody	EventCategoryEntity events) {
		return categoryService.create(events);
	}
	// READ
	@GetMapping("/get/{id}")
	public EventCategoryDTO getById(@PathVariable int id) {
		return categoryService.getById(id);
	}
	
	// UPDATE 
	@GetMapping("/update")
	public EventCategoryDTO update(@RequestBody EventCategoryEntity events) {
		return categoryService.update(events);
	}
	
	// Delete
	@GetMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return categoryService.delete(id);
	}
	
	
}
