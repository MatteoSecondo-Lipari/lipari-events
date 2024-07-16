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

    @Override
    public FullUserDTO adminChanges(FullUserDTO changes) {
        UserEntity user = userRepository.findByEmail(changes.getEmail()).orElse(null);
        if (user != null) {
            user.setEmail(changes.getEmail());
            userRepository.save(user);
        }
        return userMapper.entityToFullDto(user);
    }

    @Override
    public FullUserDTO entertainerChanges(FullUserDTO changes) {
        UserEntity user = userRepository.findByEmail(changes.getEmail()).orElse(null);
        if (user != null && user.getEntertainer() != null) {
        	user.setEmail(changes.getEmail());
            user.getEntertainer().setStageName(null);
            user.getEntertainer().setType(changes.getEntertainer().getType());;
            userRepository.save(user);
        }
        return userMapper.entityToFullDto(user);
    }

    public FullUserDTO customerChanges(FullUserDTO changes) {
        UserEntity user = userRepository.findByEmail(changes.getEmail()).orElse(null);
        if (user != null && user.getCustomer() != null) {
            user.setEmail(changes.getEmail());
            user.getCustomer().setName(changes.getCustomer().getName());
            user.getCustomer().setSurname(changes.getCustomer().getSurname());
            user.getCustomer().setTaxIdCode(changes.getCustomer().getTaxIdCode());
            user.getCustomer().setCity(changes.getCustomer().getCity());
            user.getCustomer().setPhone(changes.getCustomer().getPhone());
            user.getCustomer().setGender(changes.getCustomer().getGender());
            user.getCustomer().setBirthDate(changes.getCustomer().getBirthDate());
            userRepository.save(user);
        }
        return userMapper.entityToFullDto(user);
    }

}
