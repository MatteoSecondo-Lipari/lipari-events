package com.lipari.events.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Email(message = "Must be a valid email")
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	@Size(max = 50, message = "Must be at most 50 characters")
	private String email;
	
	@NotBlank(message = "Must be not null and must contain at least one non-whitespace character")
	@Size(max = 120, message = "Must be at most 120 characters")
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	private CustomerEntity customer;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	private EntertainerEntity entertainer;
	
	public UserEntity(@Email @NotBlank @Size(max = 50) String email, @NotBlank @Size(max = 120) String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
}
