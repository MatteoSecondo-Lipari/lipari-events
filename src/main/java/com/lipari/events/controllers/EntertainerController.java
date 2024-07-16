package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
import com.lipari.events.security.user_details.UserDetailsImpl;
import com.lipari.events.services.EntertainerService;

@RestController
@RequestMapping("/entertainer")
public class EntertainerController {
	
	@Autowired
	EntertainerService entertainerService;

	@GetMapping("/stage-name/{name}")
	public List<EntertainerDTO> getMethodName(@PathVariable String name) {
		return entertainerService.getEntertainerByStageName(name);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ENTERTAINER')")
	@GetMapping("/dashboard")
	 public List<EventStatsDashboardDTO> getAllEventStatistics() {
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
		 long entertainer_id = userDetailsImpl.getId();
		
		 List<EventStatsDashboardDTO> statistics = entertainerService.getEventStatistics(entertainer_id);
		 return statistics;
	    }
	
}
