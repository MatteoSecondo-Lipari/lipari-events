package com.lipari.events.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lipari.events.entities.EventSubcategoryEntity;
import com.lipari.events.models.EventSubcategoryDTO;
import com.lipari.events.models.EventSubcategoryDTOwithCategoryidDTO;
import com.lipari.events.models.constraints.EventSubcategoryConstraintsDTO;

@Mapper(componentModel = "spring")
public interface EventSubcategoryMapper {

	public 	EventSubcategoryDTO entityToDto(EventSubcategoryEntity entity);
	
	public EventSubcategoryEntity dtoToEntity(EventSubcategoryDTO dto);
	
	public EventSubcategoryConstraintsDTO entityToConstraintsDto(EventSubcategoryEntity entity);
	
	public EventSubcategoryEntity constraintsDtoToEntity(EventSubcategoryConstraintsDTO dto);
	
	@Mapping(source = "categoryId", target = "category.id")
	public EventSubcategoryDTOwithCategoryidDTO entityToDtowithCategoryidDTO(EventSubcategoryEntity entity);
	
	@Mapping(source = "category.id", target = "categoryId")
	public EventSubcategoryEntity DtotoEntitywithCategoryidDTO(EventSubcategoryDTOwithCategoryidDTO dto);
	
	
	
}
