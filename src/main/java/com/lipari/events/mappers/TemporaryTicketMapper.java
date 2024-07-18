package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.TemporaryTicketEntity;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.constraints.TicketConstraintsDTO;

@Mapper(componentModel = "spring")
public interface TemporaryTicketMapper {

	public TicketDTO entityToDto(TemporaryTicketEntity entity);
	public TemporaryTicketEntity dtoToEntity(TicketDTO dto);
	
	public TicketConstraintsDTO entityToConstraintsDto(TemporaryTicketEntity entity);
	public TemporaryTicketEntity constraintsDtoToEntity(TicketConstraintsDTO dto);
}
