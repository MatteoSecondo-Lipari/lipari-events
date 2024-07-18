package com.lipari.events.services;

import com.lipari.events.models.FullUserDTO;


public interface UserService {

	public FullUserDTO findUserByEmail(String email);
	
	public FullUserDTO adminChanges(FullUserDTO user, FullUserDTO changes);
	
	public FullUserDTO entertainerChanges(FullUserDTO user, FullUserDTO changes);
	
	public FullUserDTO customerChanges(FullUserDTO user, FullUserDTO changes);
}
