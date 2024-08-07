package com.lipari.events.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entertainers")
public class EntertainerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String stageName;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String type;
	
	private String stripeConnectedAccount;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entertainer", cascade = CascadeType.REMOVE)
	private List<EventsEntertainersEntity> eventsEntertainers;
}
