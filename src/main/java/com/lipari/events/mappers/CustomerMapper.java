package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.models.CustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	public CustomerDTO entityToDto(CustomerEntity entity);
	
	public CustomerEntity dtoToEntity(CustomerDTO dto);
}
