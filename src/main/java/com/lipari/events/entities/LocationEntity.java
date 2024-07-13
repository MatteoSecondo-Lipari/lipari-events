package com.lipari.events.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class LocationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Must be not null must and contain at least one non-whitespace character")
	private String city;
	
	@NotBlank(message = "Must be not null must and contain at least one non-whitespace character")
	private String address;
	
	@NotNull(message = "Must be not null")
	private int maxSeats;
	
	@NotNull(message = "Must be not null")
	private int maxNumberedSeats;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "locations")
	private List<SeatEntity> seats;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	private List<EventEntity> events;

}
