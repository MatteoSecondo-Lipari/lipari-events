package com.lipari.events.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventSubcategoryEntity;
import com.lipari.events.mappers.EventSubcategoryMapper;

import com.lipari.events.models.EventSubcategoryDTOwithCategoryidDTO;

import com.lipari.events.repositories.EventSubcategoryRepository;
import com.lipari.events.services.EventSubcategoryService;

@Service
public class EventSubcategoryServiceImpl implements EventSubcategoryService {
	
	
	@Autowired
	EventSubcategoryRepository eventSubcategoryRepository;
	
	@Autowired
	EventSubcategoryMapper eventSubcategoryMapper;

	@Override
	public EventSubcategoryDTOwithCategoryidDTO createOrUpdate(EventSubcategoryDTOwithCategoryidDTO subcategory) {
		
			EventSubcategoryEntity event = eventSubcategoryMapper.DtotoEntitywithCategoryidDTO(subcategory);
			return eventSubcategoryMapper.entityToDtowithCategoryidDTO(eventSubcategoryRepository.save(event));
		}
		


	@Override
	public List<EventSubcategoryDTOwithCategoryidDTO> getAll() {
		return eventSubcategoryRepository.findAll().stream().map(eventSubcategoryMapper::entityToDtowithCategoryidDTO).toList();
	}

	@Override
	public Optional<EventSubcategoryDTOwithCategoryidDTO> getById(int id) {
		return eventSubcategoryRepository.findById(id).map(eventSubcategoryMapper::entityToDtowithCategoryidDTO);
	}

	@Override
	public boolean delete(int id) {
		if(eventSubcategoryRepository.existsById(id))
		{
			eventSubcategoryRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
		
	}

}
