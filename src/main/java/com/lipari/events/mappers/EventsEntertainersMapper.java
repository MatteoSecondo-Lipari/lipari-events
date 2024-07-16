package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.EventsEntertainersEntity;
import com.lipari.events.models.EventsEntertainersDTO;
import com.lipari.events.models.constraints.EventsEntertainersConstraintsDTO;

@Mapper(componentModel = "spring")
public interface EventsEntertainersMapper {

	public EventsEntertainersDTO entityToDto(EventsEntertainersEntity entity);
	
	public EventsEntertainersEntity dtoToEntity(EventsEntertainersDTO dto);
	
	public EventsEntertainersConstraintsDTO entityToConstraintsDto(EventsEntertainersEntity entity);
	
	public EventsEntertainersEntity constraintsDtoToEntity(EventsEntertainersConstraintsDTO dto);
}
