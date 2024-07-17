package com.lipari.events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lipari.events.entities.LocationEntity;
import com.lipari.events.models.LocationWithEventsDTO;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

	List<LocationEntity> findById(long id);
	

	LocationEntity findById(int id);
}
