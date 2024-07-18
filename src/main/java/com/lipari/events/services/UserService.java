package com.lipari.events.services;

import java.util.List;

import com.lipari.events.models.FullUserDTO;
import com.lipari.events.models.UserWithPasswordDTO;

public interface UserService {

	public FullUserDTO findFullUserByEmail(String email);
	public UserWithPasswordDTO findUserByEmail(String email);
	
	public boolean updatePassword(UserWithPasswordDTO user);
	
	public List<FullUserDTO> getAll();
	public FullUserDTO getById(long id);
	
	public boolean deleteById(long id);
}
