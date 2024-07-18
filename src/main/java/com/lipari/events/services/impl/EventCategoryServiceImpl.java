package com.lipari.events.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventCategoryEntity;
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

	@Override
	public EventCategoryDTO create(EventCategoryEntity events) {
		EventCategoryEntity addsevent = categoryRepository.save(events);
		return categoryMapper.entityToDto(addsevent);
		
	}

	@Override
	public EventCategoryDTO getById(int id) {
		return categoryRepository.findById(id).map(categoryMapper::entityToDto).orElseThrow();
		
	}

	@Override
	public EventCategoryDTO update(EventCategoryEntity events) {
		if(categoryRepository.existsById(events.getId()))
		{
			EventCategoryEntity addsevent = categoryRepository.save(events);
			return categoryMapper.entityToDto(addsevent);
		}
		else {
			return null;
		}
		
	}

	@Override
	public boolean delete(int id) {
		if(categoryRepository.existsById(id))
		{
			categoryRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
		
	}

}
