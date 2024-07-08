package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.models.EntertainerDTO;

@Mapper(componentModel = "spring")
public interface EntertainerMapper {

	public EntertainerDTO entityToDto(EntertainerEntity entity);
	
	public EntertainerEntity dtoToEntity(EntertainerDTO dto);
}
