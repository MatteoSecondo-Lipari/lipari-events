package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.TemporaryTicketEntity;
import com.lipari.events.mappers.TemporaryTicketMapper;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.constraints.TicketConstraintsDTO;
import com.lipari.events.repositories.TemporaryTicketRepository;
import com.lipari.events.services.TemporaryTicketService;

import jakarta.transaction.Transactional;

@Service
public class TemporaryTicketServiceImpl implements TemporaryTicketService {
	
	@Autowired
	TemporaryTicketRepository temporaryTicketRepository;
	
	@Autowired
	TemporaryTicketMapper temporaryTicketMapper;

	@Override
	public List<TicketDTO> getAllByStripeTransferGroup(String transferGroup) {
		return temporaryTicketRepository.getAllByStripeTransferGroup(transferGroup)
				.stream().map(temporaryTicketMapper::entityToDto).toList();
	}

	@Override
	public boolean saveAll(List<TicketConstraintsDTO> tickets) {
		List<TemporaryTicketEntity> ticketsE =  tickets.stream().map(temporaryTicketMapper::constraintsDtoToEntity).toList();
		
		if(temporaryTicketRepository.saveAll(ticketsE) == null) {
			return false;
		}
		
		return true;
	}

	@Transactional
	@Override
	public void removeAllByTransferGroup(String transferGroup) {
		temporaryTicketRepository.deleteAllByStripeTransferGroup(transferGroup);
	}

}
