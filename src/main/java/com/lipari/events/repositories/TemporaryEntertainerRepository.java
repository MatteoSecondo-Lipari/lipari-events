package com.lipari.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.TemporaryEntertainerEntity;

public interface TemporaryEntertainerRepository extends JpaRepository<TemporaryEntertainerEntity, Long> {

	public TemporaryEntertainerEntity getByStripeConnectedAccount(String stripeAccount);
	public void deleteByStripeConnectedAccount(String stripeAccount);
}
