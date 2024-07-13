package com.lipari.events.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.TicketDTO;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@PostMapping("/purchase")
	public TicketDTO purchaseAndCreate(@RequestBody List<TicketDTO> tickets) {
		
		
		return null;
	}
}
