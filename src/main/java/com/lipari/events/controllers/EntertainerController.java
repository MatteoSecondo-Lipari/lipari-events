package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
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
	
	//@PreAuthorize("hasAnyRole('ROLE_ENTERTAINER')")
	@GetMapping("{entertainer_id}/dashboard")
	 public List<EventStatsDashboardDTO> getAllEventStatistics(@PathVariable long entertainer_id) {
		 List<EventStatsDashboardDTO> statistics = entertainerService.getEventStatistics(entertainer_id);
		 return statistics;
	    }
	
}
