package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
