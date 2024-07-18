package com.lipari.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.FullUserDTO;
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
	
	@PatchMapping("/update")
    public ResponseEntity<?> updateInfo(@RequestBody FullUserDTO usersChange) {
        
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        
        String email = userDetailsImpl.getEmail();
        
        FullUserDTO user = userService.findUserByEmail(email);
        
        if (user.getCustomer() == null && user.getEntertainer() == null) {
            // Admin logic
            return ResponseEntity.ok(userService.adminChanges(user, usersChange));
        }
        
        if (user.getCustomer() == null && user.getEntertainer() != null) {
            // Entertainer logic
            return ResponseEntity.ok(userService.entertainerChanges(user, usersChange));
        }
        
        if (user.getCustomer() != null && user.getEntertainer() == null) {
            // Customer logic
            return ResponseEntity.ok(userService.customerChanges(user, usersChange));
        }
        
        return ResponseEntity.badRequest().body("Invalid user role");
    }
	
}
