package com.lipari.events.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.mappers.TicketMapper;
import com.lipari.events.models.DashBoardDTO;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.repositories.TicketRepository;
import com.lipari.events.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketMapper ticketMapper;
	
	private DashBoardDTO dashBoardDTO = new DashBoardDTO();
	
	@Override
	public List<TicketDTO> getAll() {
		return ticketRepository.findAll().stream()
				.map(ticketMapper::EntityToDto2).toList();
	}

	@Override
	public List<TicketDTO> getByEventid(long eventid) {
		return ticketRepository.findByEventId(eventid).stream()
				.map(ticketMapper::EntityToDto2).toList();
	}
	
	@Override
	public DashBoardDTO getDashboard(long eventid) {
		
		long ticketsSold = ticketRepository.countByEventId(eventid);
		dashBoardDTO.setTicketSold(ticketsSold);
		
		return dashBoardDTO;
	}
	
	@Override
	public List<LocalDate> getPurchaseDate(long eventId){
		return ticketRepository.findPurchaseDatesByEventId(eventId);
	}
	
	
	
}