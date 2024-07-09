package com.lipari.events.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.ERole;
import com.lipari.events.models.RoleDTO;
import com.lipari.events.models.UserDTO;
import com.lipari.events.entities.UserEntity;
import com.lipari.events.payload.JwtResponse;
import com.lipari.events.payload.LoginRequest;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.payload.SignupRequest;
import com.lipari.events.repositories.UserRepository;
import com.lipari.events.security.jwt.JwtUtils;
import com.lipari.events.security.user_details.UserDetailsImpl;
import com.lipari.events.services.CustomerService;
import com.lipari.events.services.EntertainerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	EntertainerService entertainerService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
				userDetails.getId(), 
				userDetails.getEmail(), 
				roles));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid SignupRequest signupRequest) {
		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!", HttpStatus.BAD_REQUEST.value()));
		}

		// Create new user's account
		UserEntity user = new UserEntity(signupRequest.getEmail(), 
				encoder.encode(signupRequest.getPassword()));

		Set<String> strRoles = signupRequest.getRoles();
		Set<RoleDTO> roles = new HashSet<>();

		if (strRoles == null) {
			RoleDTO userRole = new RoleDTO(ERole.ROLE_CUSTOMER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleDTO adminRole = new RoleDTO(ERole.ROLE_ADMIN);
					roles.add(adminRole);
					break;
				case "entertainer":
					RoleDTO entertainerRole = new RoleDTO(ERole.ROLE_ENTERTAINER);
					roles.add(entertainerRole);
					break;
				default:
					RoleDTO userRole = new RoleDTO(ERole.ROLE_CUSTOMER);
					roles.add(userRole);
				}
			});
		}


		if(roles.size() == 1 && roles.contains(new RoleDTO(ERole.ROLE_CUSTOMER))) {
			if(signupRequest.getCustomer() == null) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Role customer provided, but no customer provided", HttpStatus.BAD_REQUEST.value()));
			}
			
			userRepository.save(user);
			signupRequest.getCustomer().setUser(new UserDTO(user.getId()));
			customerService.createOrUpdateCustomer(signupRequest.getCustomer());
		} else if(roles.size() == 1 && roles.contains(new RoleDTO(ERole.ROLE_ENTERTAINER))) {
			if(signupRequest.getEntertainer() == null) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Role entertainer provided, but no entertainer provided", HttpStatus.BAD_REQUEST.value()));
			}
			
			userRepository.save(user);
			signupRequest.getEntertainer().setUser(new UserDTO(user.getId()));
			entertainerService.createOrUpdateEntertainer(signupRequest.getEntertainer());
		} else {
			userRepository.save(user);
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!", HttpStatus.OK.value()));
	}


}
