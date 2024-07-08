package com.lipari.events.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank(message = "Must be not null and  must contain at least onenon-whitespace character")
	private String email;
	
	@NotBlank(message = "Must be not null and  must contain at least onenon-whitespace character")
	private String password;
}
