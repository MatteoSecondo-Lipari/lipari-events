package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;

@Mapper(componentModel = "spring")
public interface EntertainerMapper {

	public EntertainerDTO entityToDto(EntertainerEntity entity);
	
	public EntertainerEntity dtoToEntity(EntertainerDTO dto);
	
	public EntertainerConstraintsDTO entityToConstraintsDto(EntertainerEntity entity);
	
	public EntertainerEntity costraintsDtoToEntity(EntertainerConstraintsDTO dto);
}
