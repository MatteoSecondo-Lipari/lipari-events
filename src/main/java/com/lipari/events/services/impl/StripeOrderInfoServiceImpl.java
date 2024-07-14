package com.lipari.events.services.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.lipari.events.models.TicketDTO;
import com.lipari.events.services.StripeOrderInfoService;

@Service
public class StripeOrderInfoServiceImpl implements StripeOrderInfoService {

	private Map<String, List<TicketDTO>> unprocessedTicketsQueue = new ConcurrentHashMap<>();
	
	@Override
	public void addTicketsToQueue(String key, List<TicketDTO> tickets) {
		unprocessedTicketsQueue.put(key, tickets);
	}

	@Override
	public List<TicketDTO> getTicketsFromQueue(String key) {
		return unprocessedTicketsQueue.get(key);
	}

	@Override
	public void clearQueue(String key) {
		unprocessedTicketsQueue.get(key).clear();;
	}

}
