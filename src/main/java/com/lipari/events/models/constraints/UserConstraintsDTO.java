package com.lipari.events.models.constraints;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConstraintsDTO {

	private long id;
	
	@Email(message = "Must be a valid email")
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	@Size(max = 50, message = "Must be at most 50 characters")
	private String email;

	public UserConstraintsDTO(long id) {
		super();
		this.id = id;
	}
}
