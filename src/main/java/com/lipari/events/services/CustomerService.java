package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.constraints.CustomerConstraintsDTO;

public interface CustomerService {

	public CustomerDTO createOrUpdateCustomer(CustomerConstraintsDTO customer);
	
	public List<CustomerDTO> getAllCustomers();
	public CustomerDTO getCustomerById(long id);
	
	public CustomerDTO getCustomerByEmail(String email);
	
	public boolean deleteCustomer(long id);
}
