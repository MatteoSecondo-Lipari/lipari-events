package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.constraints.CustomerConstraintsDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	public CustomerDTO entityToDto(CustomerEntity entity);
	
	public CustomerEntity dtoToEntity(CustomerDTO dto);
	
	public CustomerConstraintsDTO entityToContraintsDto(CustomerEntity entity);
	
	public CustomerEntity constraintsDtoToEntity(CustomerConstraintsDTO dto);
	
	
}
