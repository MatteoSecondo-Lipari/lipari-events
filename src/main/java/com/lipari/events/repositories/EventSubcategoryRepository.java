package com.lipari.events.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.EventSubcategoryEntity;


public interface EventSubcategoryRepository extends JpaRepository<EventSubcategoryEntity, Integer> {
	
	Optional<EventSubcategoryEntity> findById(int id); 

}
