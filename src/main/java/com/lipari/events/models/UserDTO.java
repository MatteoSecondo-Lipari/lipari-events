package com.lipari.events.models;

import com.lipari.events.models.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private long id;
	
	private String username;
	
	private String email;

	public UserDTO(long id) {
		super();
		this.id = id;
	}
}
