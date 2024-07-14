package com.lipari.events.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventWithSubcategoryWithoutloopDTO {

	private long id;
	
	private String name;
	
	private LocalDate date;
	
	private String imagePath;
	
	private LocationDTO location;
	
	private EventsSubcategoryWithoutEventsDTO subcategory;
}
