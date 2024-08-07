package com.lipari.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	public CustomerEntity findByUserEmail(String email);

}
