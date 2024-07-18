package com.lipari.events.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;


import com.lipari.events.entities.LocationEntity;
import com.lipari.events.models.LocationDTO;
import com.lipari.events.models.LocationSeatsDTO;
import com.lipari.events.models.LocationWithEventsDTO;
import com.lipari.events.models.constraints.LocationConstraintsDTO;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	public LocationDTO entityToDto(LocationEntity entity);
	
	public LocationEntity dtoToEntity(LocationDTO dto);
	
	public LocationConstraintsDTO entityToConstraintsDto(LocationEntity entity);
	
	public LocationEntity constraintsDtoToEntity(LocationConstraintsDTO dto);
	
	public LocationWithEventsDTO entityToLocationWithEventsDTO(LocationEntity entity);
	
	public LocationEntity dtoLocationWithEventsToEntity(LocationWithEventsDTO dto);
	
	public LocationSeatsDTO entityToLocationSeatsDTODTO(LocationEntity entity);
	
	public LocationEntity dtoLocationSeatsDTOToEntity(LocationSeatsDTO dto);
	
	
}
