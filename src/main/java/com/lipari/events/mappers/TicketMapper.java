package com.lipari.events.mappers;

import org.mapstruct.Mapper;


import com.lipari.events.entities.TicketEntity;

import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.TicketOrdersDTO;


@Mapper(componentModel = "spring")
public interface TicketMapper {

	public TicketDTO entityToDto(TicketEntity entity);
	
	public TicketEntity dtoToEntity(TicketDTO dto);
	
	public TicketOrdersDTO entityToDtoTicketOrdersDTO(TicketEntity entity);
	
	public TicketEntity dtoToEntityTicketOrdersDTO(TicketOrdersDTO dto);
	
	
	
}
