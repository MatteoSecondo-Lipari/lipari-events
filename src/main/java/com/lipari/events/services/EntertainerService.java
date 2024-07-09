package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;

public interface EntertainerService {

	public EntertainerDTO createOrUpdateEntertainer(EntertainerConstraintsDTO customer);
	
	public List<EntertainerDTO> getAllEntertainers();
	public EntertainerDTO getEntertainerById();
}
