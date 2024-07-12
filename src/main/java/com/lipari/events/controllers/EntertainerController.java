package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.EntertainerDTO;
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
	
}
