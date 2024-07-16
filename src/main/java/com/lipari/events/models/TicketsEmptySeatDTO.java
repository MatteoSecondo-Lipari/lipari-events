package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketsEmptySeatDTO {
	
	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private SeatDTO seat;
	
	@Getter
	@Setter
	private EventWithSubcategoryWithoutloopDTO event;
}
