package com.lipari.events.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventWithoutLocationDTO {

	private long id;
	
	private String name;
	
	private LocalDate date;
	
	private String imagePath;
	
	private EventsSubcategoryWithoutEventsDTO subcategory;
}
