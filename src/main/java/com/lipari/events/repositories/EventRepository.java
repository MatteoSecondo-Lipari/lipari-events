package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long>{

	List<EventEntity> findEventByNameStartingWith(String name);
	
	//List<EventEntity> findEventByentertainers(String entertainers);
}
