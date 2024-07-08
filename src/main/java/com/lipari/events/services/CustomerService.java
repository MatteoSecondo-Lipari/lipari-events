package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.CustomerDTO;

public interface CustomerService {

	public CustomerDTO createOrUpdateCustomer(CustomerDTO customer);
	
	public List<CustomerDTO> getAllCustomers();
	public CustomerDTO getCustomerById();
}
