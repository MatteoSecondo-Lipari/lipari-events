package com.lipari.events.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSubcategoryDTO {

	private int id;
	
	private String name;
	
	private List<EventWithoutSubcategoryDTO> events;
}
