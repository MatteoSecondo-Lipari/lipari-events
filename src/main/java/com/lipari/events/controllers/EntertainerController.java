package com.lipari.events.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.entities.EntertainerEntity;
import com.lipari.events.models.EntertainerDTO;
import com.lipari.events.models.EventStatsDashboardDTO;
import com.lipari.events.models.constraints.EntertainerConstraintsDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.repositories.UserRepository;

import com.lipari.events.security.user_details.UserDetailsImpl;
import com.lipari.events.services.EntertainerService;
import com.lipari.events.services.StripeRequestsStorageService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;


@RestController
@RequestMapping("/entertainer")
public class EntertainerController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntertainerService entertainerService;
	
	@Autowired
	StripeRequestsStorageService stripeRequestsStorageService;

	@GetMapping("/stage-name/{name}")
	public List<EntertainerDTO> getMethodName(@PathVariable String name) {
		return entertainerService.getEntertainerByStageName(name);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ENTERTAINER')")
	@GetMapping("/dashboard")
	 public List<EventStatsDashboardDTO> getAllEventStatistics() {
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
  		 String email = userDetailsImpl.getEmail();
  		 long entertainer_id= userRepository.findByEmail(email).get().getEntertainer().getId();
		
		 List<EventStatsDashboardDTO> statistics = entertainerService.getEventStatistics(entertainer_id);
		 return statistics;
	    }

	@PreAuthorize("hasAnyRole('ROLE_ENTERTAINER')")
	@GetMapping("/onboarding")
	public ResponseEntity<?> onboarding() {

		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		

		EntertainerEntity entertainer = userRepository.findByEmail(userDetailsImpl.getEmail())
				.get().getEntertainer();
		
		if(entertainer.getStripeConnectedAccount() != null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
					new MessageResponse("Current user (entertainer) has already a stripe account connected",
							HttpStatus.FORBIDDEN.value()));
		}
		
		try {
			Account account = entertainerService.createStripeAccount();
			AccountLink accountLink = entertainerService.linkToOnboarding(account.getId());
			
			stripeRequestsStorageService.addEntertainer(account.getId(), entertainer);
			
			return ResponseEntity.ok(accountLink.toJson());
		} catch (StripeException e) {
			return ResponseEntity.internalServerError().body(
					new MessageResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}
	
	@PostMapping("/onboarding-webhook")
	public ResponseEntity<?> saveTicketsAndDoTrasfers(
			@RequestHeader("Stripe-Signature") String stripeSignature,
			@RequestBody String body) {
		String webhookSecret = "whsec_e994884d0dceb279ea9e3d2c91e88ee64af2924837e68d6c242e1243390ed5fc";
		Event event;

		try {
			event = Webhook.constructEvent(body, stripeSignature, webhookSecret);
		} catch (SignatureVerificationException e) {
			return ResponseEntity.badRequest().body(
					new MessageResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
		
		EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
		StripeObject stripeObject = null;
		if (dataObjectDeserializer.getObject().isPresent()) {
			stripeObject = dataObjectDeserializer.getObject().get();
		} else {
			return ResponseEntity.internalServerError().body(
					new MessageResponse("Error with stripe object deserialization", 500));
		}

		switch (event.getType()) {
		case "account.updated":
			Account account = (Account)stripeObject;
			
			if(account.getCapabilities().getTransfers().equals("active")) {
				//acount onboarding success, save stripe account here in entertainer table				
				EntertainerEntity entertainer = stripeRequestsStorageService.getEntertainer(account.getId());
				
				entertainer.setStripeConnectedAccount(account.getId());
				entertainerService.update(entertainer);
			}
			
			break;
		default:
			break;
		}

		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/all")
	public List<EntertainerDTO> getAll(EntertainerDTO category) {
		return entertainerService.getAll();
	}
	
	// READ
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(entertainerService.getById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(new MessageResponse("No entertainer found", 404));
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public EntertainerDTO update(@RequestBody EntertainerEntity events) {
		return entertainerService.update(events);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable long id) {
		return entertainerService.delete(id);
	}
	
}
