package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.entities.TicketEntity;
import com.lipari.events.models.CustomerDTO;


public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

	List<TicketEntity> findByCustomer(CustomerEntity customer);

	
}
