package com.lipari.events.models.constraints;

import java.time.LocalDate;

import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.SeatDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketConstraintsDTO {

	private int id;
	
	private LocalDate purchaseDate;
	
	private SeatDTO seat;
	
	@NotNull(message = "Must not be null")
	private EventDTO event;
	
	private CustomerDTO customer;
}
