package com.lipari.events.payload;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;
	private List<String> roles;
	
	public JwtResponse(String token, Long id, String email, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
	
}
