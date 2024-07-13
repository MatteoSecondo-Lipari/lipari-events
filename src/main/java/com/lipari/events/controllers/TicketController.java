package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.EventDTO;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.EventService;
import com.lipari.events.services.TicketService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	EventService eventService;

	@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
	@PostMapping("/purchase")
	public ResponseEntity<?> purchaseAndCreate(@RequestBody List<TicketDTO> tickets) {
		long ticketPrice;
		
		EventDTO event = eventService.getEventById(tickets.getFirst().getEvent().getId());
		
		if(tickets.getFirst().getSeat() == null) {
			ticketPrice = (long)event.getTicketPrice() * 100;
		} else {
			ticketPrice = (long)event.getNumberedTicketPrice() * 100;
		}
		
		try {
			return ResponseEntity.ok(ticketService.purchase(tickets, ticketPrice));
		} catch (StripeException e) {
			return ResponseEntity.internalServerError().body(
					new MessageResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}
}
