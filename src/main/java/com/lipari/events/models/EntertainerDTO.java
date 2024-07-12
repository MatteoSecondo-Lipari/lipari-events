package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntertainerDTO {

	private long id;

	private String stageName;
	
	private String type;
	
	private UserDTO user;

	public EntertainerDTO(long id) {
		super();
		this.id = id;
	}
	
}
