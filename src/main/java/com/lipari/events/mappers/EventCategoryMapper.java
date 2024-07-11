package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.EventCategoryEntity;
import com.lipari.events.models.EventCategoryDTO;
import com.lipari.events.models.constraints.EventCategoryConstraintsDTO;

@Mapper(componentModel = "spring", uses = EventMapper.class)
public interface EventCategoryMapper {

	public EventCategoryDTO entityToDto(EventCategoryEntity entity);
	
	public EventCategoryEntity dtoToEntity(EventCategoryDTO dto);
	
	public EventCategoryConstraintsDTO entityToConstraintsDto(EventCategoryEntity entity);
	
	public EventCategoryEntity constraintsDtoToEntity(EventCategoryConstraintsDTO dto);
	
}
