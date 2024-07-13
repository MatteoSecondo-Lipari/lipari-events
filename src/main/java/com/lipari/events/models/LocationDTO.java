package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

	private int id;
	
	private String city;
	
	private String address;
}
