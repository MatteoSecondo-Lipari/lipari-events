package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.DashBoardDTO;
import com.lipari.events.models.TicketDTO;

public interface TicketService {
	
	public List<TicketDTO> getAll();
	
	public List<TicketDTO> getByEventid(long eventid);
	
	public DashBoardDTO getDashboard(long eventid);
	
}