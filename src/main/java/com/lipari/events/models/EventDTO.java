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
	
	private String location;
	
	private LocalDate date;
	
	private EventCategoryDTO category;
}
