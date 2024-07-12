package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.EventSubcategoryEntity;
import com.lipari.events.models.EventSubcategoryDTO;
import com.lipari.events.models.constraints.EventSubcategoryConstraintsDTO;

@Mapper(componentModel = "spring")
public interface EventSubcategoryMapper {

	public 	EventSubcategoryDTO entityToDto(EventSubcategoryEntity entity);
	
	public EventSubcategoryEntity dtoToEntity(EventSubcategoryDTO dto);
	
	public EventSubcategoryConstraintsDTO entityToConstraintsDto(EventSubcategoryEntity entity);
	
	public EventSubcategoryEntity constraintsDtoToEntity(EventSubcategoryConstraintsDTO dto);
}
