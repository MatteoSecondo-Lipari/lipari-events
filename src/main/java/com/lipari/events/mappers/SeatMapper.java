package com.lipari.events.mappers;


import org.mapstruct.Mapper;



import com.lipari.events.entities.SeatEntity;

import com.lipari.events.models.SeatDTO;


@Mapper(componentModel = "spring")
public interface SeatMapper {

	public SeatDTO entityToDto(SeatEntity entity);
	
	public SeatEntity dtoToEntity(SeatDTO dto);
	
	
}
