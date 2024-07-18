package com.lipari.events.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.entities.UserEntity;
import com.lipari.events.mappers.UserMapper;
import com.lipari.events.models.FullUserDTO;
import com.lipari.events.models.UserDTO;
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
    public FullUserDTO adminChanges(FullUserDTO user, FullUserDTO changes) {
    	UserEntity u = userMapper.fullDtoToEntity(user);
       
        u.setEmail(changes.getEmail());
            
        return userMapper.entityToFullDto(userRepository.save(u));
    }

    @Override
    public FullUserDTO entertainerChanges(FullUserDTO user, FullUserDTO changes) {
        UserEntity u = userMapper.fullDtoToEntity(user);
       
        u.setEmail(changes.getEmail());
        u.getEntertainer().setStageName(null);
        u.getEntertainer().setType(changes.getEntertainer().getType());
        
        return userMapper.entityToFullDto(userRepository.save(u));
    }

    public FullUserDTO customerChanges(FullUserDTO user, FullUserDTO changes) {
    	UserEntity u = userMapper.fullDtoToEntity(user);
        
    	u.setEmail(changes.getEmail());
    	u.getCustomer().setName(changes.getCustomer().getName());
    	u.getCustomer().setSurname(changes.getCustomer().getSurname());
    	u.getCustomer().setTaxIdCode(changes.getCustomer().getTaxIdCode());
    	u.getCustomer().setCity(changes.getCustomer().getCity());
    	u.getCustomer().setPhone(changes.getCustomer().getPhone());
        u.getCustomer().setGender(changes.getCustomer().getGender());
        u.getCustomer().setBirthDate(changes.getCustomer().getBirthDate());
      
        return userMapper.entityToFullDto(userRepository.save(u));
    }

}
