package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EventCategoryDTO;
import com.lipari.events.models.EventSubcategoryDTO;

public interface EventSubcategoryService {

	public EventSubcategoryDTO createOrUpdate(EventCategoryDTO subcategory);
	
	public List<EventSubcategoryDTO> getAll();
	public EventSubcategoryDTO getById(int id);

}
