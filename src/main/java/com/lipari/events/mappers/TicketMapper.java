package com.lipari.events.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.lipari.events.entities.TicketEntity;
import com.lipari.events.models.TicketDTO;

@Mapper(componentModel = "spring")
public interface TicketMapper {

	public TicketDTO entityToDto(TicketEntity entity);
	
	public TicketEntity dtoToEntity(TicketDTO dto);
	
	public List<TicketDTO> entitiesToDtos(List<TicketEntity> entity);
	
	public List<TicketEntity> dtosToEntities(List<TicketDTO> dto);
}
