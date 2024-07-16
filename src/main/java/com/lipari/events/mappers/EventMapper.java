package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.EventEntity;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.EventWithoutSubcategoryDTO;
import com.lipari.events.models.constraints.EventConstraintsDTO;

@Mapper(componentModel = "spring", uses = LocationMapper.class)
public interface EventMapper {

	public EventDTO entityToDto(EventEntity entity);
	
	public EventEntity dtoToEntity(EventDTO dto);
	
	public EventConstraintsDTO entityToConstraintsDto(EventEntity entity);
	
	public EventEntity constraintsDtoToEntity(EventConstraintsDTO dto);
	
	public EventWithoutSubcategoryDTO entityToDtoWithoutSubcategory(EventEntity entity);
	
	public EventEntity dtoWithoutSubcategoryToEntity(EventWithoutSubcategoryDTO dto);
}
