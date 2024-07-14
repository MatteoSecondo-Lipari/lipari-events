package com.lipari.events.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EntertainerNNEventsDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;

@Mapper(componentModel = "spring")
public interface EntertainerMapper {	
	
	public EntertainerDTO entityToDto(EntertainerEntity entity);
	
	public EntertainerEntity dtoToEntity(EntertainerDTO dto);
	
	public EntertainerConstraintsDTO entityToConstraintsDto(EntertainerEntity entity);
	
	public EntertainerEntity costraintsDtoToEntity(EntertainerConstraintsDTO dto);
	
	 @Mapping(target = "events", source = "events")
	 public EntertainerNNEventsDTO entityNNToDto(EntertainerEntity entity);
	    
	 @Mapping(target = "events", source = "events")
	 public EntertainerEntity dtoNNToEntity(EntertainerNNEventsDTO dto);
}
