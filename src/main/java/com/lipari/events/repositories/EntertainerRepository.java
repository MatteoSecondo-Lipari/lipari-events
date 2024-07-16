package com.lipari.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lipari.events.entities.EntertainerEntity;


public interface EntertainerRepository extends JpaRepository<EntertainerEntity, Long> {

	public List<EntertainerEntity> getByStageNameStartingWith(String stageName);

}
