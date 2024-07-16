package com.lipari.events.models;

import com.lipari.events.models.UserWithPasswordDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPasswordDTO {

	private long id;
	
	private String email;

	private String password;
}
