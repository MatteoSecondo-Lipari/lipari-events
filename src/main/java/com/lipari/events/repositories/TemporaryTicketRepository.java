package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.TemporaryTicketEntity;

public interface TemporaryTicketRepository extends JpaRepository<TemporaryTicketEntity, Long> {
  
	public List<TemporaryTicketEntity> getAllByStripeTransferGroup(String transferGroup);
	public void deleteAllByStripeTransferGroup(String transferGroup);
}
