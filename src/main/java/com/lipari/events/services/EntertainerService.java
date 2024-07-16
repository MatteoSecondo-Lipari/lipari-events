package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EntertainerNNEventsDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;

public interface EntertainerService {

	public EntertainerDTO createOrUpdateEntertainer(EntertainerConstraintsDTO customer);
	
	public List<EntertainerDTO> getAllEntertainers();
	public EntertainerDTO getEntertainerById();
	public List<EntertainerDTO> getEntertainerByStageName(String stageName);
	public List<EntertainerNNEventsDTO> getEventWithEntertainers(String entertainers);
	
	public List<EventStatsDashboardDTO> getEventStatistics(long event_id);
	
	public EventStatsDashboardDTO mapToEventStatsDashboardDTO(Object[] result);
}
