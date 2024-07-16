package com.lipari.events.services;

import com.lipari.events.models.FullUserDTO;

public interface UserService {

	public FullUserDTO findUserByEmail(String email);
	
	public FullUserDTO adminChanges(FullUserDTO changes);
	
	public FullUserDTO entertainerChanges(FullUserDTO changes);
	
	public FullUserDTO customerChanges(FullUserDTO changes);
}
