package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardDTO {
	
	private long soldTicket;
	
	private int maxSeats;
	
	private float priceStandingSeats;
	
	private float priceSeatedSeats;
	
	private float totalRevenue;

	public void setTicketSold(long ticketsSold) {
		this.soldTicket = ticketsSold;
	}
	
}