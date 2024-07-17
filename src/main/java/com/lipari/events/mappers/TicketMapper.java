package com.lipari.events.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import com.lipari.events.entities.TicketEntity;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.TicketOrdersDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;
import com.lipari.events.models.constraints.TicketConstraintsDTO;

@Mapper(componentModel = "spring")
public interface TicketMapper {

	public TicketDTO entityToDto(TicketEntity entity);
	public TicketEntity dtoToEntity(TicketDTO dto);
	
	public TicketConstraintsDTO entityToConstraintDto(TicketEntity entity);
	public TicketEntity constraintDtoToEntity(TicketConstraintsDTO dto);
	
	public TicketOrdersDTO entityToDtoTicketOrdersDTO(TicketEntity entity);
	public TicketEntity dtoToEntityTicketOrdersDTO(TicketOrdersDTO dto);
	
	public TicketsEmptySeatDTO entityToDtoTicketsEmptySeatDTO(TicketEntity entity);
	public TicketEntity dtoToEntityTicketsEmptySeatDTO(TicketsEmptySeatDTO dto);
	
	public List<TicketDTO> entitiesToDtos(List<TicketEntity> entity);
	public List<TicketEntity> dtosToEntities(List<TicketDTO> dto);
	
	public List<TicketConstraintsDTO> entitiesToConstraintsDtos(List<TicketEntity> entity);
	public List<TicketEntity> constraintsDtosToEntities(List<TicketConstraintsDTO> dto);
}
