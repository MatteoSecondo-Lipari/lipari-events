package com.lipari.events.models.constraints;

import java.time.LocalDate;

import com.lipari.events.models.EventSubcategoryDTO;
import com.lipari.events.models.LocationDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventConstraintsDTO {

	private long id;
	
	@NotBlank(message = "Must be not null must and contain at least one non-whitespace character")
	private String name;
	
	@Future(message = "Must be a future date")
	private LocalDate date;
	
	@NotNull(message = "Must be not null")
	private LocationDTO location;
	
	@NotNull(message = "Must be not null")
	private EventSubcategoryDTO subcategory;
}
