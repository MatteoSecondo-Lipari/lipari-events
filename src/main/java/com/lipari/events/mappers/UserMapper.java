package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.UserEntity;
import com.lipari.events.models.FullUserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public FullUserDTO entityToFullDto(UserEntity entity);
	
	public UserEntity fullDtoToEntity(FullUserDTO dto);
}
