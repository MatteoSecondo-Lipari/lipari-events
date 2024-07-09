package com.lipari.events.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String name;
	
	@NotBlank(message = "Must be not null and  must contain at least one non-whitespace character")
	private String surname;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String taxIdCode;

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String city;

	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private EGender gender;
	
	@Past(message = "Must be a past date")
	private LocalDate birthDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<TicketEntity> tickets;

}
