package com.lipari.events.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.entities.EventEntity;
import com.lipari.events.mappers.CustomerMapper;
import com.lipari.events.models.CustomerDTO;
import com.lipari.events.models.ERole;
import com.lipari.events.models.EventDTO;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.models.TicketOrdersDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;
import com.lipari.events.models.constraints.TicketConstraintsDTO;
import com.lipari.events.payload.MessageResponse;
import com.lipari.events.repositories.UserRepository;
import com.lipari.events.security.user_details.UserDetailsImpl;
import com.lipari.events.services.CustomerService;
import com.lipari.events.services.EventService;
import com.lipari.events.services.TemporaryTicketService;
import com.lipari.events.services.TicketService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	TicketService ticketService;
	
	@Autowired
	TemporaryTicketService temporaryTicketService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;

	//purchase tickets
	@PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
	@PostMapping("/checkout")
	public ResponseEntity<?> purchaseAndCreate(@RequestBody List<@Valid TicketConstraintsDTO> tickets) {
		long ticketPrice;
		EventDTO event;

		try {
			event = eventService.getEventDTOById(tickets.getFirst().getEvent().getId());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Event not found", HttpStatus.NOT_FOUND.value()));
		}
		
		//check if tickets that have to be purchased can be purchased (consider maxTicketsNumber and maxNumberedTicketsNumber)
		if(tickets.getFirst().getSeat() == null) {
			if(event.getLocation().getMaxSeats() < tickets.size() + ticketService.countTicketsByEventId(event.getId())) {
				return ResponseEntity.internalServerError().body(
						new MessageResponse("There aren't enough seats available", HttpStatus.INTERNAL_SERVER_ERROR.value()));
			}
			
			ticketPrice = (long)event.getTicketPrice() * 100;
		} else {
			if(event.getLocation().getMaxNumberedSeats() < tickets.size() + ticketService.countNumberedTicketsByEventId(event.getId())) {
				return ResponseEntity.internalServerError().body(
						new MessageResponse("There aren't enough numbered seats available", HttpStatus.INTERNAL_SERVER_ERROR.value()));
			}
			
			ticketPrice = (long)event.getNumberedTicketPrice() * 100;
		}
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		String authority = userDetailsImpl.getAuthorities()
				.stream().findFirst().get().getAuthority();
		
		//tickets can be purchased
		if(authority.equals(ERole.ROLE_CUSTOMER.name())) {
			String email = userDetailsImpl.getEmail();
			CustomerEntity customerE = userRepository.findByEmail(email).get().getCustomer();
			
			tickets.forEach(t -> {
				t.setPurchaseDate(LocalDate.now());
				t.setEvent(event);
				t.setCustomer(new CustomerDTO(customerE.getId()));
			});
			
			try {
				String transferGroup = UUID.randomUUID().toString();
				tickets.forEach(t -> t.setStripeTransferGroup(transferGroup));
				temporaryTicketService.saveAll(tickets);
				
				return ResponseEntity.ok(ticketService.checkout(tickets, ticketPrice, transferGroup));
			} catch (StripeException e) {
				return ResponseEntity.internalServerError().body(
						new MessageResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
			}
		} else {
			
			if(tickets.getFirst().getCustomer() == null) {
				return ResponseEntity.badRequest().body(
						new MessageResponse("customer: Must be not null", 400));
			}
			
			tickets.forEach(t -> {
				t.setPurchaseDate(LocalDate.now());
				t.setEvent(event);
				t.setCustomer(tickets.getFirst().getCustomer());
			});
			
			if(!ticketService.saveAllConstrints(tickets)) {
				return ResponseEntity.internalServerError().body(
						new MessageResponse("Something went wrong saving tickets", HttpStatus.INTERNAL_SERVER_ERROR.value()));
			}
			
			return ResponseEntity.ok(new MessageResponse("Tickets saved successfully", 201));
		}
	}

	//saving tickets in database and transfer amount to entertainers
	@PostMapping("/checkout-webhook")
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
		case "payment_intent.succeeded":
			PaymentIntent paymentIntent = (PaymentIntent) stripeObject;

			String transferGroup = paymentIntent.getTransferGroup();
			List<TicketDTO> tickets = temporaryTicketService.getAllByStripeTransferGroup(transferGroup);
			
			tickets.forEach(t -> t.setId(0));
			ticketService.saveAll(tickets);
			
			long eventId = tickets.getFirst().getEvent().getId();
			EventEntity e =  eventService.getEventEntityById(eventId);
			
			try {
				ticketService.transfers(e.getEventsEntertainers(), transferGroup, paymentIntent.getAmount());
			} catch (StripeException e1) {
				System.out.println(e1.getMessage());
				return ResponseEntity.internalServerError().body(
						new MessageResponse(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
			}
			
			temporaryTicketService.removeAllByTransferGroup(transferGroup);
			break;
		default:
			break;
		}

		return ResponseEntity.ok().build();
	}
	
	//get all tickets purchased by a customer
	@PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
	@GetMapping("/orders")
	public List<TicketOrdersDTO> Orders() {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl)SecurityContextHolder.getContext().
				getAuthentication().getPrincipal();
		
		CustomerDTO customerDTO = customerService.getCustomerByEmail(userDetailsImpl.getEmail());
		CustomerEntity customerEntity = customerMapper.dtoToEntity(customerDTO);
		return ticketService.getAllByCustomerId(customerEntity);
	}
	
	@GetMapping("/all/{event}")
	public List<TicketsEmptySeatDTO> allseatsByEventId(@PathVariable long event){
		return ticketService.getAllTicketByEventId(event);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public ResponseEntity<?> putMethodName(@RequestBody @Valid TicketConstraintsDTO ticket) {
		
		if(ticket.getId() == 0) {
			return ResponseEntity.badRequest().body(
					new MessageResponse("id: Must not be null", 400));
		}
		
		if(ticket.getCustomer() == null) {
			return ResponseEntity.badRequest().body(
					new MessageResponse("customer: Must not be null", 400));
		}
		
		return ResponseEntity.ok(ticketService.updateTicket(ticket));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTicket(@PathVariable long id) {
		
		if(!ticketService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new MessageResponse("Ticket not existing", 404));
		}
		
		return ResponseEntity.ok().body(
				new MessageResponse("Ticket deleted successfully", 200));
	}

}
