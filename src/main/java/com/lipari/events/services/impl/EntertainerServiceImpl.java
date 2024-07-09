package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.mappers.EntertainerMapper;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;
import com.lipari.events.repositories.EntertainerRepository;
import com.lipari.events.services.EntertainerService;

@Service
public class EntertainerServiceImpl implements EntertainerService {
	
	@Autowired
	EntertainerRepository entertainerRepository;
	
	@Autowired
	EntertainerMapper entertainerMapper;

	@Override
	public EntertainerDTO createOrUpdateEntertainer(EntertainerConstraintsDTO entertainer) {
		EntertainerEntity ee = entertainerMapper.costraintsDtoToEntity(entertainer);
		return entertainerMapper.entityToDto(entertainerRepository.save(ee));
	}

	@Override
	public List<EntertainerDTO> getAllEntertainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntertainerDTO getEntertainerById() {
		// TODO Auto-generated method stub
		return null;
	}

}
