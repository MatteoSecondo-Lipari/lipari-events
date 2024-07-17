package com.lipari.events.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lipari.events.models.EventSubcategoryDTOwithCategoryidDTO;


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
	@GetMapping("/create-update")
	public EventSubcategoryDTOwithCategoryidDTO create(@RequestBody	EventSubcategoryDTOwithCategoryidDTO events) {
		return eventSubcategoryService.createOrUpdate(events);
	}
	
	// READ
	@GetMapping("/get/{id}")
	public Optional<EventSubcategoryDTOwithCategoryidDTO> getById(@PathVariable int id) {
		return eventSubcategoryService.getById(id);
	}
	
	// DELETE
	@GetMapping("/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return eventSubcategoryService.delete(id);
	}
	
	
}
