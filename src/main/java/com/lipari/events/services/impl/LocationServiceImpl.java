package com.lipari.events.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.mappers.EventMapper;
import com.lipari.events.mappers.LocationMapper;
import com.lipari.events.mappers.TicketMapper;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;
import com.lipari.events.models.SeatDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;
import com.lipari.events.repositories.EventRepository;
import com.lipari.events.repositories.LocationRepository;
import com.lipari.events.repositories.TicketRepository;
import com.lipari.events.services.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	LocationMapper locationMapper;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventMapper eventMapper;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketMapper ticketMapper;
	
	@Override
	public List<LocationWithEventsDTO> getAllLocation() {
		return locationRepository.findAll().stream()
				.map(locationMapper::entityToLocationWithEventsDTO).toList();
	}
	
	@Override
	public List<LocationSeatsDTO> getAllSeats(){
		return locationRepository.findAll().stream()
				.map(locationMapper::entityToLocationSeatsDTODTO).toList();
	}
	
	@Override
	public List<LocationSeatsDTO> getAvailableSeatsForEvent(long eventId) {
	  
	    EventDTO event = eventMapper.entityToDto(eventRepository.findById(eventId)
	            .orElseThrow(() -> new RuntimeException("Evento non trovato con ID: " + eventId)));

	   
	    long locationId = event.getLocation().getId();

	    // Per prendere tutti i seat dispinibili per quell'evento.
	    List<LocationSeatsDTO> locationSeatsDTOs = locationRepository.findById(locationId)
	            .stream().map(locationMapper::entityToLocationSeatsDTODTO).toList();
	         

	    // Per prendere tutti i ticket quindi tutti i seat presi.
	    List<TicketsEmptySeatDTO> allTickets = ticketRepository.findAll()
	            .stream().map(ticketMapper::entityToDtoTicketsEmptySeatDTO).toList();

	    
	    // Per prendere tutti i posti presenti e immagazzinarli nella lista ticket.
	    List<SeatDTO> occupiedSeats = allTickets.stream().map(TicketsEmptySeatDTO::getSeat).toList();

	    
	    // Confronta i posti e li salva in una lista
	    for(LocationSeatsDTO LocationSeat : locationSeatsDTOs)
	    {
	    	List<SeatDTO> avaibleSeats = LocationSeat.getSeats().stream()
	    			.filter(seat -> !occupiedSeats.contains(seat)).toList();
	    	
	    	LocationSeat.setSeats(avaibleSeats);
	    }

	    return locationSeatsDTOs;
	}


}