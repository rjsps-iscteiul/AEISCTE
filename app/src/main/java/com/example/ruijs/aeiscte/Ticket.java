package com.example.ruijs.aeiscte;

import java.util.Random;

public class Ticket{
    private String eventId;
    private String ticketId;

    public Ticket(String eventId){
        this.eventId = eventId;
        this.ticketId = randomNewTicketId(eventId);
    }

    private String randomNewTicketId(String eventId){
        Random random = new Random();
        return String.valueOf(random.nextInt(100000))+eventId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getEventId() {
        return eventId;
    }
}
