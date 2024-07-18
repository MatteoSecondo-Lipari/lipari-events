package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.TemporaryEntertainerEntity;
import com.lipari.events.models.EntertainerDTO;

@Mapper(componentModel = "spring")
public interface TemporaryEntertainerMapper {

	public EntertainerDTO entityToDto(TemporaryEntertainerEntity entity);
	public TemporaryEntertainerEntity dtoToEntity(EntertainerDTO dto);

}
