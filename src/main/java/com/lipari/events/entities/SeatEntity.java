package com.lipari.events.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class SeatEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String number;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "seats_locations",
		joinColumns = @JoinColumn(name = "seat_id"),
		inverseJoinColumns = @JoinColumn(name = "location_id")
	)	
	private List<LocationEntity> locations;
}
