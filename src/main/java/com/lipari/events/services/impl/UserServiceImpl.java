package com.lipari.events.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.UserEntity;
import com.lipari.events.mappers.UserMapper;
import com.lipari.events.models.FullUserDTO;
import com.lipari.events.repositories.UserRepository;
import com.lipari.events.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;

	@Override
	public FullUserDTO findUserByEmail(String email) {
		UserEntity user = userRepository.findByEmail(email).orElse(null);
		return userMapper.entityToFullDto(user);
	}

}
