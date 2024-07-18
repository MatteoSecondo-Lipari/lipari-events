package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketsEmptySeatDTO {
	
	private int id;
	
	private SeatDTO seat;
	
	private EventWithSubcategoryWithoutloopDTO event;
}
