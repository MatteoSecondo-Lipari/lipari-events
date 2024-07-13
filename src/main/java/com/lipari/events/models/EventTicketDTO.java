package com.lipari.events.models;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTicketDTO {

	private long id;
	
	private String name;
	
	private LocalDate date;
	
	private String imagePath;
	
	private float ticketPrice;
	
	private float numberedTicketPrice;
	
	private LocationwithTicketDTO location;
	
	private EventsSubcategoryWithoutEventsDTO subcategory;
}
