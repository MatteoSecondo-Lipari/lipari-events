package com.lipari.events.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCategoryDTOWithoutSubcategory {

	private int id;
	
	private String name;
	
}
