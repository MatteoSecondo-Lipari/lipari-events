package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.constraints.CustomerConstraintsDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public CustomerDTO updateCustomer(@RequestBody @Valid CustomerConstraintsDTO customer) {
		CustomerDTO c = customerService.getCustomerById(customer.getId());
		customer.setUser(c.getUser());
		
		return customerService.createOrUpdateCustomer(customer);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/all")
	public List<CustomerDTO> getAll() {
		return customerService.getAllCustomers();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public CustomerDTO getById(@PathVariable long id) {
		return customerService.getCustomerById(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long id){
		
		if(!customerService.deleteCustomer(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Customer not existing", 404));
		}
		
		return ResponseEntity.ok().body(
				new MessageResponse("Customer deleted successfully", 200));
	}
}
