package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntertainerDTO {

	private long id;
	
	private long entertainerId;

	private String stageName;
	
	private String stripe_connected_account;
	
	private String type;
	
	private String stripeConnectedAccount;
	
	private UserDTO user;

	public EntertainerDTO(long id) {
		super();
		this.id = id;
	}
	
}
