package com.lipari.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long>{

}
