package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.FullUserDTO;
import com.lipari.events.models.UserWithPasswordDTO;
import com.lipari.events.payload.ChangePasswordRequest;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.security.user_details.UserDetailsImpl;
import com.lipari.events.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/info")
	public ResponseEntity<?> getInfo() {
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
		String email = userDetailsImpl.getEmail();
		
		return ResponseEntity.ok(userService.findFullUserByEmail(email));
	}
	
	@PostMapping("/update/password")
	public ResponseEntity<?> postMethodName(@RequestBody @Valid ChangePasswordRequest request) {
		
		if(!request.getNewPassword().equals(request.getConfirmPassword())) {
			return ResponseEntity.badRequest().body(
					new MessageResponse("Passwords must be the same", 400));
		}
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
		String email = userDetailsImpl.getEmail();
		
		UserWithPasswordDTO user =  userService.findUserByEmail(email);
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		
		if(!userService.updatePassword(user)) {
			return ResponseEntity.internalServerError().body(
					new MessageResponse("Password update failed", 500));
		}
		
		return ResponseEntity.ok(new MessageResponse("Password updated successfully", 200));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/all")
	public List<FullUserDTO> getall() {
		return userService.getAll();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public FullUserDTO getById(@PathVariable long id) {
		return userService.getById(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable long id) {

		if(!userService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("User not existing", 404));
		}

		return ResponseEntity.ok().body(
				new MessageResponse("User deleted successfully", 200));
	}

}
