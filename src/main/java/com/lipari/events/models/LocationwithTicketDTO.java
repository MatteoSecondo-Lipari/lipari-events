package com.lipari.events.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationwithTicketDTO {

	private int id;
	
	private String city;
	
	private String address;
	
	private int maxNumberedSeats;
	
	private int maxSeats;
}
