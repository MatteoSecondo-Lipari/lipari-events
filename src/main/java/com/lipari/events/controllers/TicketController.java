package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.mappers.CustomerMapper;
import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.TicketOrdersDTO;
import com.lipari.events.services.CustomerService;
import com.lipari.events.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@PostMapping("/purchase")
	public TicketDTO purchaseAndCreate(@RequestBody List<TicketDTO> tickets) {
		
		
		return null;
	}
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	//Manca che preleva automaticamente l'email dell'user loggato.
	
	@GetMapping("orders/{email}")
	public List<TicketOrdersDTO> Orders (@PathVariable String email)
	{
		CustomerDTO customerDTO = customerService.getCustomerByEmail(email);
		CustomerEntity customerEntity = customerMapper.dtoToEntity(customerDTO);
		return ticketService.getAllByCustomerId(customerEntity);
	}
}
