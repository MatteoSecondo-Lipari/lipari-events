package com.lipari.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.EntertainerService;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;


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
	@GetMapping("/onboarding")
	public ResponseEntity<?> onboarding() {
		try {
			Account account = entertainerService.createStripeAccount();
			AccountLink accountLink = entertainerService.linkToOnboarding(account.getId());
			
			return ResponseEntity.ok(accountLink.toJson());
		} catch (StripeException e) {
			return ResponseEntity.internalServerError().body(
					new MessageResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}
	
	
}
