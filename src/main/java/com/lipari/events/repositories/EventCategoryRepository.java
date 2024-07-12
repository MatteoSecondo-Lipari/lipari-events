package com.lipari.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.EventCategoryEntity;

public interface EventCategoryRepository extends JpaRepository<EventCategoryEntity, Integer> {

}
