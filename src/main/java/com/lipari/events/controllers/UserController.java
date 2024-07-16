package com.lipari.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.security.user_details.UserDetailsImpl;
import com.lipari.events.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/info")
	public ResponseEntity<?> getInfo() {
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
		String email = userDetailsImpl.getEmail();
		
		return ResponseEntity.ok(userService.findUserByEmail(email));
	}
	
}
