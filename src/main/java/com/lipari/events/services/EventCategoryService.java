package com.lipari.events.services;

import java.util.List;
import java.util.Optional;

import com.lipari.events.entities.EventCategoryEntity;
import com.lipari.events.models.EventCategoryDTO;

public interface EventCategoryService {

	public List<EventCategoryDTO>getAll();
	
	
	// CRUD Operations
	
	// CREATE 
	public EventCategoryDTO create(EventCategoryEntity events);
	// READ
	
	public Optional<EventCategoryDTO> getById(int id);
	
	// UPDATE 
	
	public EventCategoryDTO update(EventCategoryEntity events);
	
	// Delete
	public boolean delete(int id);

	
	
	
	
	
	
	
}
