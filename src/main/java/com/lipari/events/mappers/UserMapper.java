package com.lipari.events.mappers;

import org.mapstruct.Mapper;

import com.lipari.events.entities.UserEntity;
import com.lipari.events.models.FullUserDTO;
import com.lipari.events.models.UserDTO;
import com.lipari.events.models.UserWithPasswordDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public UserDTO entityToDto(UserEntity entity);
	
	public UserEntity dtoToEntity(UserDTO dto);
	
	public FullUserDTO entityToFullDto(UserEntity entity);
	
	public UserEntity fullDtoToEntity(FullUserDTO dto);
	
	public UserWithPasswordDTO entityToUserWithPasswordDto(UserEntity entity);
	
	public UserEntity userWithPasswordDtoToEntity(UserWithPasswordDTO dto);
}
