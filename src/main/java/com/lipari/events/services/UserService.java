package com.lipari.events.services;

import com.lipari.events.models.FullUserDTO;

public interface UserService {

	public FullUserDTO findUserByEmail(String email);
}
