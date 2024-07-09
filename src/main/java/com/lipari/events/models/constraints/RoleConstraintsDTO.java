package com.lipari.events.models.constraints;

import com.lipari.events.models.ERole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleConstraintsDTO {

	@Enumerated(EnumType.STRING)
	private ERole name;
}
