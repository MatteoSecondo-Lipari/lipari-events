package com.lipari.events.payload;

import com.lipari.events.models.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerResponse {

	private CustomerDTO customer;
	
	private String userPassword;
}
