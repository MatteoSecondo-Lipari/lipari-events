package com.lipari.events.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	@Size(min = 8, max = 16, message = "Must be between 8 and 16 characters")
	private String newPassword;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	@Size(min = 8, max = 16, message = "Must be between 8 and 16 characters")
	private String confirmPassword;
	
}
