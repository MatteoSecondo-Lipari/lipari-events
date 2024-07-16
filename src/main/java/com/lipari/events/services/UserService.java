package com.lipari.events.services;

import com.lipari.events.models.FullUserDTO;
import com.lipari.events.models.UserWithPasswordDTO;

public interface UserService {

	public FullUserDTO findFullUserByEmail(String email);
	public UserWithPasswordDTO findUserByEmail(String email);
	
	public boolean updatePassword(UserWithPasswordDTO user);
}
