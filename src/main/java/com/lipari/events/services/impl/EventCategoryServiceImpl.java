package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.mappers.EventCategoryMapper;
import com.lipari.events.models.EventCategoryDTO;
import com.lipari.events.repositories.EventCategoryRepository;
import com.lipari.events.services.EventCategoryService;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {
	
	@Autowired
	EventCategoryRepository categoryRepository;
	
	@Autowired
	EventCategoryMapper categoryMapper;

	@Override
	public List<EventCategoryDTO> getAll() {
		return categoryRepository.findAll().stream()
				.map(categoryMapper::entityToDto).toList();
	}

}
