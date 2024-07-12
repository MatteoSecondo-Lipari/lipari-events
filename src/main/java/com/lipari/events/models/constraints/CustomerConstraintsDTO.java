package com.lipari.events.models.constraints;

import java.time.LocalDate;

import com.lipari.events.models.EGender;
import com.lipari.events.models.UserDTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerConstraintsDTO {

	private long id;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String name;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String surname;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String taxIdCode;

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String city;

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private EGender gender;
	
	@Past(message = "Must be a past date")
	private LocalDate birthDate;
	
	private UserDTO user;
}
