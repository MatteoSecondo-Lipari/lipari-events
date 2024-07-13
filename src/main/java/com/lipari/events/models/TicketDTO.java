package com.lipari.events.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

	private int id;
	
	private LocalDate purchaseDate;
	
	private SeatDTO seat;
	
	private EventDTO event;
	
	private CustomerDTO customer;
}
