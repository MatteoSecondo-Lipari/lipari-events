package com.lipari.events.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.TicketEntity;
import com.lipari.events.mappers.TicketMapper;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.repositories.TicketRepository;
import com.lipari.events.services.TicketService;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketMapper ticketMapper;

	@Override
	public Session purchase(List<TicketDTO> tickets, long price) throws StripeException {
		StripeClient client = new StripeClient("sk_test_51OrGCUGjZ7RLeJMqT3ykVjC3DJmA0w3YxBCsBRJEmqpo6U493CM8368ug0bWxxjQqimkU30mi0ZSq9Y89PFWedqS00PRErjXsK");

		SessionCreateParams params =
		  SessionCreateParams.builder()
		    .addLineItem(
		      SessionCreateParams.LineItem.builder()
		        .setPriceData(
		          SessionCreateParams.LineItem.PriceData.builder()
		            .setCurrency("eur")
		            .setProductData(
		              SessionCreateParams.LineItem.PriceData.ProductData.builder()
		                .setName("Tickets for event: " + " " + tickets.getFirst().getEvent().getName())
		                .build()
		            )
		            .setUnitAmount(price)
		            .build()
		        )
		        .setQuantity((long)tickets.size())
		        .build()
		    )
		    .setPaymentIntentData(
		      SessionCreateParams.PaymentIntentData.builder().setTransferGroup(UUID.randomUUID().toString()).build()
		    )
		    .setMode(SessionCreateParams.Mode.PAYMENT)
		    .setSuccessUrl("https://google.com")
		    .build();

		return client.checkout().sessions().create(params);
	}

	@Override
	public long getTicketPrice(long id) {
		TicketEntity ticketE = ticketRepository.findById(id).orElseThrow();
		
		if(ticketE.getSeat() == null) {
			return (long)ticketE.getEvent().getTicketPrice();
		}
		
		return (long)ticketE.getEvent().getNumberedTicketPrice() * 100;
	}

}
