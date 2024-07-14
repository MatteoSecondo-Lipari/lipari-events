package com.lipari.events.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lipari.events.models.EventDTO;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.services.EventService;
import com.lipari.events.services.StripeOrderInfoService;
import com.lipari.events.services.TicketService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;


@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	StripeOrderInfoService stripeOrderInfoService;

	@Autowired
	EventService eventService;

	@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
	@PostMapping("/purchase")
	public ResponseEntity<?> purchaseAndCreate(@RequestBody List<TicketDTO> tickets) {
		long ticketPrice;
		EventDTO event;

		try {
			event = eventService.getEventById(tickets.getFirst().getEvent().getId());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Event not found", HttpStatus.NOT_FOUND.value()));
		}

		if(tickets.getFirst().getSeat() == null) {
			ticketPrice = (long)event.getTicketPrice() * 100;
		} else {
			ticketPrice = (long)event.getNumberedTicketPrice() * 100;
		}

		tickets.forEach(t -> t.setPurchaseDate(LocalDate.now()));

		try {
			String key = UUID.randomUUID().toString();

			stripeOrderInfoService.addTicketsToQueue(key, tickets);
			return ResponseEntity.ok(ticketService.checkout(tickets, ticketPrice, key));
		} catch (StripeException e) {
			return ResponseEntity.internalServerError().body(
					new MessageResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}

	@PostMapping("/checkout-webhook")
	public ResponseEntity<?> postMethodName(
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
		case "payment_intent.succeeded":
			PaymentIntent paymentIntent = (PaymentIntent) stripeObject;

			String key = paymentIntent.getTransferGroup();

			ticketService.saveAll(stripeOrderInfoService.getTicketsFromQueue(key));
			stripeOrderInfoService.clearQueue(key);
			
			//TODO: trasfer amount to entertainers
			
			break;
		default:
			break;
		}

		return ResponseEntity.ok().build();
	}

}
