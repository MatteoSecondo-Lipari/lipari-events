package com.lipari.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

	
	
}
