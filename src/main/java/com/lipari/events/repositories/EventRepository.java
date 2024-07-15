package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lipari.events.entities.EventEntity;
import java.time.LocalDate;


public interface EventRepository extends JpaRepository<EventEntity, Long>{

	List<EventEntity> findEventByNameStartingWith(String name);
	
	@Query("SELECT e FROM EventEntity e ORDER BY e.date DESC LIMIT 20")
    List<EventEntity> findTop20ByDateOrderDesc();
}
