package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.mappers.CustomerMapper;
import com.lipari.events.models.CustomerDTO;
import com.lipari.events.repositories.CustomerRepository;
import com.lipari.events.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerMapper customerMapper;

	@Override
	public CustomerDTO createOrUpdateCustomer(CustomerDTO customer) {
		CustomerEntity ce = customerMapper.dtoToEntity(customer);
		return customerMapper.entityToDto(customerRepository.save(ce));
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDTO getCustomerById() {
		// TODO Auto-generated method stub
		return null;
	}

}
