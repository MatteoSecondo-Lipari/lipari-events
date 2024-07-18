package com.lipari.events.services;

import com.lipari.events.models.EntertainerDTO;

public interface TemporaryEntertainerService {

	public boolean save(EntertainerDTO entertainer);
	public EntertainerDTO getByStripeConnectedAccount(String stripeAccount);
	public void removeByStripeConnectedAccount(String stripeAccount);
}
