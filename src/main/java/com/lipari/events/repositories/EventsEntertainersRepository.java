package com.lipari.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.EventsEntertainersEntity;

public interface EventsEntertainersRepository extends JpaRepository<EventsEntertainersEntity, Long> {

}
