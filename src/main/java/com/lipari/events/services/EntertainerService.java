package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;

public interface EntertainerService {

	public EntertainerDTO createOrUpdateEntertainer(EntertainerConstraintsDTO customer);
	
	public List<EntertainerDTO> getAllEntertainers();
	public EntertainerDTO getEntertainerById();
	public List<EntertainerDTO> getEntertainerByStageName(String stageName);
	
	public Account createStripeAccount() throws StripeException;
	public AccountLink linkToOnboarding(String accountId) throws StripeException;
}
