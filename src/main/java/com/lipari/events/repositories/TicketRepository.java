package com.lipari.events.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lipari.events.entities.TicketEntity;
import com.lipari.events.models.TicketDTO;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

	List<TicketEntity> findByEventId(long eventId); 
	
	long countByEventId(long eventId);
	
	@Query(value = "select purchase_date FROM tickets t WHERE t.event.id = 1", nativeQuery = true)
	List<LocalDate> findPurchaseDatesByEventId(long eventId);
	
	
	 
	//@Query("Select t.id from TicketEntity t where event_id = ?1")
	//float getTotalRevenueByEventId(long eventId);
	
	
}
