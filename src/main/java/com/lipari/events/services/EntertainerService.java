package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EntertainerDTO;

public interface EntertainerService {

	public EntertainerDTO createOrUpdateEntertainer(EntertainerDTO customer);
	
	public List<EntertainerDTO> getAllEntertainers();
	public EntertainerDTO getEntertainerById();
}
