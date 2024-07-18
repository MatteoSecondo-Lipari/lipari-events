package com.lipari.events.services;

import java.util.List;



import com.lipari.events.entities.SeatEntity;
import com.lipari.events.models.SeatDTO;

public interface SeatService {
	
	public List<SeatDTO> getAll();
	
	public SeatDTO createOrUpdate(SeatEntity seat);
	public SeatDTO getById(int id);
	public boolean delete(int id);
	
}