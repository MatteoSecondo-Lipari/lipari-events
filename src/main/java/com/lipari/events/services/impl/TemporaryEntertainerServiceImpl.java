package com.lipari.events.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.TemporaryEntertainerEntity;
import com.lipari.events.mappers.TemporaryEntertainerMapper;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.repositories.TemporaryEntertainerRepository;
import com.lipari.events.services.TemporaryEntertainerService;

import jakarta.transaction.Transactional;

@Service
public class TemporaryEntertainerServiceImpl implements TemporaryEntertainerService {
	
	@Autowired
	TemporaryEntertainerRepository temporaryEntertainerRepository;
	
	@Autowired
	TemporaryEntertainerMapper temporaryEntertainerMapper;

	@Override
	public boolean save(EntertainerDTO entertainer) {
		TemporaryEntertainerEntity ee = temporaryEntertainerMapper.dtoToEntity(entertainer);
		
		if(temporaryEntertainerRepository.save(ee) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public EntertainerDTO getByStripeConnectedAccount(String stripeAccount) {
		return temporaryEntertainerMapper.entityToDto(
				temporaryEntertainerRepository.getByStripeConnectedAccount(stripeAccount));
	}

	@Transactional
	@Override
	public void removeByStripeConnectedAccount(String stripeAccount) {
		temporaryEntertainerRepository.deleteByStripeConnectedAccount(stripeAccount);
	}

}
