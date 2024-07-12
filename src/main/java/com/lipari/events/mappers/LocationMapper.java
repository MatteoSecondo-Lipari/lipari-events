package com.lipari.events.mappers;

import org.mapstruct.Mapper;


import com.lipari.events.entities.LocationEntity;
import com.lipari.events.models.LocationDTO;
import com.lipari.events.models.constraints.LocationConstraintsDTO;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	public LocationDTO entityToDto(LocationEntity entity);
	
	public LocationEntity dtoToEntity(LocationDTO dto);
	
	public LocationConstraintsDTO entityToConstraintsDto(LocationEntity entity);
	
	public LocationEntity constraintsDtoToEntity(LocationConstraintsDTO dto);
	
}
