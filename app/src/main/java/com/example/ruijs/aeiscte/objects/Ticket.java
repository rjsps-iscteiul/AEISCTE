package com.example.ruijs.aeiscte.objects;

import com.example.ruijs.aeiscte.Card;
import com.example.ruijs.aeiscte.FeedFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Ticket{
    private String name;
    private String date;
    private String eventId;
    private String ticketId;
    private String userId;
    private boolean validated=false;

    public Ticket(){

    }

    public Ticket(String eventId){
        this.eventId = eventId;
        this.ticketId = randomNewTicketId(eventId);
    }

    private String randomNewTicketId(String eventId){
        Random random = new Random();
        int n = Integer.parseInt(eventId)+1234;
        return String.valueOf(random.nextInt(100000)*n);
    }

    public boolean getIsValidated() {
        return validated;
    }

    public void setIsValidated(boolean validated) {
        this.validated = validated;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

}
