package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.entities.TicketEntity;


public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
  
	public int countBySeatIsNullAndEventId(long id);
	public int countBySeatIsNotNullAndEventId(long id);
	public List<TicketEntity> findByCustomer(CustomerEntity customer);
	
	public List<TicketEntity> findByEventId(long eventid);

}
