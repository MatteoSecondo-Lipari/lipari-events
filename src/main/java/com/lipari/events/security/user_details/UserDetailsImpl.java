package com.lipari.events.security.user_details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lipari.events.entities.UserEntity;
import com.lipari.events.models.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserDetailsImpl build(UserEntity user) {
		List<ERole> userRoles = new ArrayList<>();
		
		if(user.getCustomer() != null) {
			userRoles.add(ERole.ROLE_CUSTOMER);
		}
		
		if(user.getEntertainer() != null) {
			userRoles.add(ERole.ROLE_ENTERTAINER);
		}
		
		if(user.getCustomer() == null && user.getEntertainer() == null) {
			userRoles.add(ERole.ROLE_ADMIN);
		}
		
		List<GrantedAuthority> authorities = userRoles.stream()
				.map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList());
		
		return new UserDetailsImpl(
				user.getId(),
				user.getEmail(),
				user.getPassword(),
				authorities
		);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return null;
	}

}
