package com.lipari.events.services.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.services.StripeRequestsStorageService;

@Service
public class StripeOrderInfoServiceImpl implements StripeRequestsStorageService {

	//key=stripe_transferGroup
	private Map<String, List<TicketDTO>> unprocessedTicketsQueue = new ConcurrentHashMap<>();
	
	//key=stripe_accountId
	private Map<String, EntertainerEntity> entertainers = new ConcurrentHashMap<>();
	
	//TODO: manage all methods with if statement to check operations results
	
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

	@Override
	public void addEntertainer(String key, EntertainerEntity entertainer) {
		entertainers.put(key, entertainer);
	}

	@Override
	public EntertainerEntity getEntertainer(String key) {
		return entertainers.get(key);
	}

	@Override
	public void removeEntertainer(String key) {
		entertainers.remove(key);
	}

}
