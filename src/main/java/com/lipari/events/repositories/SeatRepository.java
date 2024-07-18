package com.lipari.events.repositories;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.lipari.events.entities.SeatEntity;


@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Integer> {



}
