package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.constraints.TicketConstraintsDTO;

public interface TemporaryTicketService {

	public boolean saveAll(List<TicketConstraintsDTO> tickets);
	public List<TicketDTO> getAllByStripeTransferGroup(String transferGroup);
	public void removeAllByTransferGroup(String transferGroup);
}
