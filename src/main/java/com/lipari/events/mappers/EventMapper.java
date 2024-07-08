package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.models.EventDTO;

@Mapper(componentModel = "spring")
public interface EventMapper {

public EventDTO entityToDto(EventEntity entity);
	
	public EventEntity dtoToEntity(EventDTO dto);
}
