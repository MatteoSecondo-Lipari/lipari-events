package com.lipari.events.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Must be not null must and contain at least one non-whitespace character")
	private String name;
	
	@NotBlank(message = "Must be not null must and contain at least one non-whitespace character")
	private String location;
	
	@Enumerated(EnumType.STRING)
	private ECategory category;
	
	@Future(message = "Must be a future date")
	private LocalDate date;
}
