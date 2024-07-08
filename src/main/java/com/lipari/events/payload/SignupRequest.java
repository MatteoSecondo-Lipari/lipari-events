package com.lipari.events.payload;

import java.util.Set;

import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.EntertainerDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	@Size(max = 50, message = "Must be at most 50 characters")
	@Email(message = "Must be a valid email")
	private String email;

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	@Size(min = 8, max = 16, message = "Must be between 8 and 16 characters")
	private String password;
	
	private Set<String> roles;
	
	private CustomerDTO customer;
	private EntertainerDTO entertainer;
}
