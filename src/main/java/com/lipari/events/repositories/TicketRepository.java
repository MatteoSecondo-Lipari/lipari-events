package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.entities.TicketEntity;
import com.lipari.events.models.CustomerDTO;


public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
  
	public int countBySeatIsNullAndEventId(long id);
	public int countBySeatIsNotNullAndEventId(long id);
	List<TicketEntity> findByCustomer(CustomerEntity customer);
	
	List<TicketEntity> findByEventId(long eventid);

}
