package com.lipari.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventStatsDashboardDTO {

    private long eventId;
    private String eventName;
    private float seatsPrice;
    private float standPrice;
    private int locationSeatsCapacity;
    private int locationMaxCapacity;
    private long ticketsSold;
    private long remainingTickets;
    private long numberOfSeatsTicketsSold;
    private long numberOfStandingTicketsSold;
    private float totalRevenue;

}
