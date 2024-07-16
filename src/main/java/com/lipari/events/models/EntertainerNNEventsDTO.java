package com.lipari.events.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntertainerNNEventsDTO {

	

	private String stageName;

	private List<EventWithSubcategoryWithoutloopDTO> events;
	
	
}
