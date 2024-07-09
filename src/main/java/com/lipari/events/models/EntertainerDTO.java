package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntertainerDTO {

	private long id;

	private String stageName;
	
	private String type;
	
	private UserDTO user;
}
