package com.lipari.events.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.events.entities.EventsEntertainersEntity;
import com.lipari.events.entities.TicketEntity;
import com.lipari.events.mappers.TicketMapper;
import com.lipari.events.models.TicketDTO;
import com.lipari.events.repositories.TicketRepository;
import com.lipari.events.services.TicketService;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.param.TransferCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

import com.lipari.events.entities.CustomerEntity;
import com.lipari.events.mappers.CustomerMapper;

import com.lipari.events.models.TicketOrdersDTO;
import com.lipari.events.models.TicketsEmptySeatDTO;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketMapper ticketMapper;
  
  @Autowired 
	CustomerMapper customerMapper;

	@Override
	public String checkout(List<TicketDTO> tickets, long price, String transferGroup) throws StripeException {
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
		      SessionCreateParams.PaymentIntentData.builder().setTransferGroup(transferGroup).build()
		    )
		    .setMode(SessionCreateParams.Mode.PAYMENT)
		    .setSuccessUrl("http://localhost:4200")
		    .setCancelUrl("http://localhost:4200")
		    .build();

		return client.checkout().sessions().create(params).getLastResponse().body();
	}

	@Override
	public boolean saveAll(List<TicketDTO> tickets) {
		List<TicketEntity> ticketsE =  ticketMapper.dtosToEntities(tickets);
		
		if(ticketRepository.saveAll(ticketsE) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public void transfers(List<EventsEntertainersEntity> eventsEntertainers, String transferGroup, long totalAmount) throws StripeException {
		StripeClient client = new StripeClient("sk_test_51OrGCUGjZ7RLeJMqT3ykVjC3DJmA0w3YxBCsBRJEmqpo6U493CM8368ug0bWxxjQqimkU30mi0ZSq9Y89PFWedqS00PRErjXsK");

		//removing fees to the amount to transfer (5%)
		totalAmount -= totalAmount / 100 * 5;
		
		for (EventsEntertainersEntity eventEntertainer : eventsEntertainers) {
			String destination = eventEntertainer.getEntertainer().getStripeConnectedAccount();
			long amount = (long)(totalAmount / 100 * eventEntertainer.getPercentage());
			
			TransferCreateParams params =
					TransferCreateParams.builder()
					.setAmount(amount)
					.setCurrency("eur")
					.setDestination(destination)
					.setTransferGroup(transferGroup)
					.build();

			client.transfers().create(params);
		}
	}

	@Override
	public int countTicketsByEventId(long id) {
		return ticketRepository.countBySeatIsNullAndEventId(id);
	}
	
	@Override
	public int countNumberedTicketsByEventId(long id) {
		return ticketRepository.countBySeatIsNotNullAndEventId(id);
	}

}

	@Override
	public List<TicketOrdersDTO> getAllByCustomerId(CustomerEntity customer) {
		return ticketRepository.findByCustomer(customer).stream()
				.map(ticketMapper::entityToDtoTicketOrdersDTO).toList();
	}

	@Override
	public List<TicketsEmptySeatDTO> getAllTicketByEventId(long eventid){
		return ticketRepository.findByEventId(eventid).stream()
				.map(ticketMapper::entityToDtoTicketsEmptySeatDTO).toList();
	}
	
}
