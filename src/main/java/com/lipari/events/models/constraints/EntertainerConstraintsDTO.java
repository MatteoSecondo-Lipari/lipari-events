package com.lipari.events.models.constraints;

import com.lipari.events.models.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntertainerConstraintsDTO {

	private long id;

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String stageName;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String type;
	
	@NotNull(message = "Must be not null")
	private UserDTO user;
}
