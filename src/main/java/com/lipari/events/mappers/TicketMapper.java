package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.TicketEntity;
import com.lipari.events.models.TicketDTO;

@Mapper(componentModel = "spring")
public interface TicketMapper {

	public TicketDTO entityToDto(TicketEntity entity);
	
	public TicketEntity dtoToEntity(TicketDTO dto);
}
