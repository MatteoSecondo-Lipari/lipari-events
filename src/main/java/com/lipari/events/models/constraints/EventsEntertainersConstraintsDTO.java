package com.lipari.events.models.constraints;

import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.EventWithSubcategoryWithoutloopDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventsEntertainersConstraintsDTO {

	private long id;
	
	@NotNull(message = "Must not be null")
	private EventWithSubcategoryWithoutloopDTO event;
	
	@NotNull(message = "Must not be null")
	private EntertainerDTO entertainer;
	
	@NotNull(message = "Must not be null")
	private float percentage;

	public EventsEntertainersConstraintsDTO(@NotNull(message = "Must not be null") EventWithSubcategoryWithoutloopDTO event,
			@NotNull(message = "Must not be null") EntertainerDTO entertainer,
			@NotNull(message = "Must not be null") float percentage) {
		super();
		this.event = event;
		this.entertainer = entertainer;
		this.percentage = percentage;
	}

}
