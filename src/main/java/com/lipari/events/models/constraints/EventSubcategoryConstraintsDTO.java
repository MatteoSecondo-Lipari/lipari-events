package com.lipari.events.models.constraints;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSubcategoryConstraintsDTO {

	private int id;
	
	@NotBlank(message = "Must be not null must and contain at least one non-whitespace character")
	private String name;
}
