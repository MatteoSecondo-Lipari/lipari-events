package com.lipari.events.services;

import java.util.List;
import java.util.Optional;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.entities.EventCategoryEntity;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EntertainerNNEventsDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;

public interface EntertainerService {

	public List<EntertainerDTO> getEntertainerByStageName(String stageName);

	public Account createStripeAccount() throws StripeException;
	public AccountLink linkToOnboarding(String accountId) throws StripeException;
  
	public List<EntertainerNNEventsDTO> getEventWithEntertainers(String entertainers);
  
	public List<EventStatsDashboardDTO> getEventStatistics(long event_id);
	
	public EventStatsDashboardDTO mapToEventStatsDashboardDTO(Object[] result);
	
	//CRUD
	public List<EntertainerDTO> getAll();
	public EntertainerDTO createEntertainer(EntertainerConstraintsDTO entertainer);
	public Optional<EntertainerDTO> getById(long id);
	public EntertainerDTO update(EntertainerEntity events);
	public boolean delete(long id);

}
