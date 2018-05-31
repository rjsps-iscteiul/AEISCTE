package com.example.ruijs.aeiscte.objects;

import com.example.ruijs.aeiscte.Card;

import java.util.Random;

public class Ticket{
    private String name;
    private String date;
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

    public Card getCard(){
        Card card = new Card(name, "Tickets", date, 0);
        card.setTicket(this);
        return card;
    }
}
