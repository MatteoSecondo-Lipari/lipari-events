package com.lipari.events.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.DashBoardDTO;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	
	@Autowired
	TicketService ticketService;
	
	@PostMapping("/purchase")
	public TicketDTO purchaseAndCreate(@RequestBody List<TicketDTO> tickets) {
		return null;
	}
	
	@GetMapping("/getall")
	public List<TicketDTO> searchall() {
		return ticketService.getAll();
		
	}

	@GetMapping("/find/{eventid}")
	public DashBoardDTO getByEventid(@PathVariable long eventid) {
		return ticketService.getDashboard(eventid);
	}
	
	@GetMapping("/purchase/{eventid}")
	public List<LocalDate> getPurchaseDate(@PathVariable long eventid) {
		return ticketService.getPurchaseDate(eventid);
	
	}
}
