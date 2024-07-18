package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.mappers.CustomerMapper;
import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.constraints.CustomerConstraintsDTO;
import com.lipari.events.repositories.CustomerRepository;
import com.lipari.events.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerMapper customerMapper;

	@Override
	public CustomerDTO createOrUpdateCustomer(CustomerConstraintsDTO customer) {
		CustomerEntity ce = customerMapper.constraintsDtoToEntity(customer);
		return customerMapper.entityToDto(customerRepository.save(ce));
	}
	
	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll()
				.stream().map(customerMapper::entityToDto).toList();
	}

	@Override
	public CustomerDTO getCustomerById(long id) {
		return customerMapper.entityToDto(
				customerRepository.findById(id).orElse(null));
	}

	
	@Override
	public CustomerDTO getCustomerByEmail(String email) {
	    CustomerEntity customerEntity = customerRepository.findByUserEmail(email);
	    return customerMapper.entityToDto(customerEntity);
	}

	@Override
	public boolean deleteCustomer(long id) {
		
		if(!customerRepository.existsById(id)) {
			return false;
		}
		
		customerRepository.deleteById(id);
		return true;
	}
	
}
