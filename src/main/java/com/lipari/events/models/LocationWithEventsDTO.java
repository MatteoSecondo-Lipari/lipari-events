package com.lipari.events.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationWithEventsDTO {

	private int id;
	
	private String city;
	
	private List<EventWithoutLocationDTO> events;
}
