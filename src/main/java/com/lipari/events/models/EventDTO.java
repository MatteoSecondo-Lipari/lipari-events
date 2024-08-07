package com.lipari.events.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

	private long id;
	
	private String name;
	
	private LocalDate date;
	
	private String imagePath;
	
	private LocationDTO location;
	
	private EventSubcategoryDTO subcategory;
	
	private float ticketPrice;
	
	private float numberedTicketPrice;
}
