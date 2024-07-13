package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.TicketEntity;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.TicketWithoutEventDTO;

@Mapper(componentModel = "spring")
public interface TicketMapper{
	
	TicketWithoutEventDTO EntitytoDto(TicketEntity entity);
	
	TicketEntity dtoToEntity(TicketWithoutEventDTO dto);
	
	TicketDTO EntityToDto2(TicketEntity entity);
	
	TicketEntity DtoToEntity2(TicketDTO dto);
}