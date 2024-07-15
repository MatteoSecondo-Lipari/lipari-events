package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.mappers.CustomerMapper;
import com.lipari.events.mappers.TicketMapper;
import com.lipari.events.models.TicketOrdersDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;
import com.lipari.events.repositories.TicketRepository;
import com.lipari.events.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketMapper ticketMapper;
	
	@Autowired 
	CustomerMapper customerMapper;
	
	
	@Override
	public List<TicketOrdersDTO> getAllByCustomerId(CustomerEntity customer) {
		return ticketRepository.findByCustomer(customer).stream()
				.map(ticketMapper::entityToDtoTicketOrdersDTO).toList();
	}

	@Override
	public List<TicketsEmptySeatDTO> getAll(){
		return ticketRepository.findAll().stream()
				.map(ticketMapper::entityToDtoTicketsEmptySeatDTO).toList();
	}
	
}