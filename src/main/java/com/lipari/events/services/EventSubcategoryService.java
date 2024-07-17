package com.lipari.events.services;

import java.util.List;
import java.util.Optional;


import com.lipari.events.models.EventSubcategoryDTOwithCategoryidDTO;

public interface EventSubcategoryService {

	public EventSubcategoryDTOwithCategoryidDTO createOrUpdate(EventSubcategoryDTOwithCategoryidDTO subcategory);
	
	public List<EventSubcategoryDTOwithCategoryidDTO> getAll();
	public Optional<EventSubcategoryDTOwithCategoryidDTO> getById(int id);
	public boolean delete(int id);

}
