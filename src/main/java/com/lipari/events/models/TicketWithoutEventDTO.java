package com.lipari.events.models;

import java.time.LocalDate;

import com.lipari.events.entities.EventEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketWithoutEventDTO {

	private int id;
	
	private LocalDate purchaseDate;
	
	private SeatDTO seat;
	
	private EventEntity event;
	
	private CustomerDTO customer;
}
