package com.lipari.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.EventCategoryEntity;
import com.lipari.events.models.EventCategoryDTO;

public interface EventCategoryRepository extends JpaRepository<EventCategoryEntity, Integer> {
	
	EventCategoryDTO findById(int id);

}
