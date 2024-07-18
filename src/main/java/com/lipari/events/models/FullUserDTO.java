package com.lipari.events.models;

import com.lipari.events.models.FullUserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullUserDTO {

	private long id;
	
	private String email;

	private CustomerWithoutUserDTO customer;
	
	private EntertainerWithoutUserDTO entertainer;
}
