package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsEntertainersDTO {

	private long id;
	
	private EventDTO event;
	
	private EntertainerDTO entertainer;
	
	private float percentage;
}
