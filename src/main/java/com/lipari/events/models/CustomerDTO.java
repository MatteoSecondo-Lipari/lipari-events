package com.lipari.events.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private long id;
	
	private String name;
	
	private String surname;
	
	private String taxIdCode;

	private String city;

	private String phone;
	
	private EGender gender;
	
	private LocalDate birthDate;
	
	private UserDTO user;

	public CustomerDTO(long id) {
		super();
		this.id = id;
	}
}
