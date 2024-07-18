package com.lipari.events.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventCategoryEntity;
import com.lipari.events.entities.LocationEntity;
import com.lipari.events.mappers.LocationMapper;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.LocationDTO;
import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;
import com.lipari.events.models.SeatDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;
import com.lipari.events.repositories.LocationRepository;
import com.lipari.events.services.EventService;
import com.lipari.events.services.LocationService;
import com.lipari.events.services.TicketService;

@Service
public class LocationServiceImpl implements LocationService{

	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	LocationMapper locationMapper;

	@Autowired
	EventService eventService;
	
	@Autowired
	TicketService ticketService;
	
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
	  
	    EventDTO event = eventService.getEventDTOById(eventId);

	    int locationId = event.getLocation().getId();

	    // Per prendere tutti i seat dispinibili per quell'evento.
	    List<LocationSeatsDTO> locationSeatsDTOs = locationRepository.findById(locationId)
	            .stream().map(locationMapper::entityToLocationSeatsDTODTO).toList();
	         

	    // Per prendere tutti i seat occupati per quell'evento
	    List<TicketsEmptySeatDTO> allTickets = ticketService.getAllTicketByEventId(eventId);

	    
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

	@Override
	public LocationDTO createOrUpdate(LocationEntity location) {
		LocationEntity addlocations = locationRepository.save(location);
		return locationMapper.entityToDto(addlocations);
	}

	@Override
	public LocationDTO getById(int id) {
	    return locationRepository.findById(id).map(locationMapper::entityToDto).orElseThrow();
	}

	@Override
	public boolean delete(int id) {
		LocationEntity searchLocation = locationRepository.findById(id).orElseThrow();
		if(searchLocation != null)
		{
			locationRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
		
	}


}