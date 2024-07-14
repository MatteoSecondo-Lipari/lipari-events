package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.models.EventStatsDashboardDTO;

public interface EventRepository extends JpaRepository<EventEntity, Long>{

	List<EventEntity> findEventByNameStartingWith(String name);

	 @Query(value = "SELECT " +
	            "    events.id AS event_id, " +
	            "    events.name AS event_name, " +
	            "    events.numbered_ticket_price AS seats_price, " +
	            "    events.ticket_price AS stand_price, " +
	            "    location.max_numbered_seats AS location_seats_capacity, " +
	            "    location.max_seats AS location_max_capacity, " +
	            "    COUNT(ticket.id) AS tickets_sold, " +
	            "    location.max_seats - COUNT(ticket.id) AS remaining_ticket, " +
	            "    COUNT(CASE WHEN seat.number IS NOT NULL THEN ticket.id END) AS number_of_seats_tickets_sold, " +
	            "    COUNT(CASE WHEN seat.number IS NULL THEN ticket.id END) AS number_of_standing_tickets_sold, " +
	            "    SUM( " +
	            "        CASE " +
	            "            WHEN seat.number IS NOT NULL THEN events.numbered_ticket_price " +
	            "            ELSE events.ticket_price " +
	            "        END " +
	            "    ) AS total_revenue " +
	            "FROM " +
	            "    events events " +
	            "JOIN " +
	            "    locations location ON events.location_id = location.id " +
	            "JOIN " +
	            "    tickets ticket ON ticket.event_id = events.id " +
	            "JOIN " +
	            "    seats seat ON ticket.seat_id = seat.id " +
	            "GROUP BY " +
	            "    events.id", nativeQuery = true)
	    List<EventStatsDashboardDTO> getEventStatistics();
}
