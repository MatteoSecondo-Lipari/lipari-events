package com.lipari.events.models;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationSeatsDTO {
	
	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private String city;
	
	@Getter
	@Setter
	private String address;
	
	@Getter
	@Setter
	private List<SeatDTO> seats;
}
