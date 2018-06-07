package com.example.ruijs.aeiscte;


import com.example.ruijs.aeiscte.objects.News;
import com.example.ruijs.aeiscte.objects.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Card implements Comparable<Card>{

    private String name="TÍTULO";
    private String category="CATEGORIA";
    private String date="DATA";
    private int image;
    private boolean isFeed=true;
    private News feed;
    private Ticket ticket;

    public Card(){

    }

    public Card(String name, String category, String date, int image){

        this.name = name;
        this.category = category;
        this.date = date;
        this.image = image;
    }

    public boolean getIsFeed() {
        return isFeed;
    }

    public News getFeed() {
        return feed;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        isFeed = false;
        this.ticket = ticket;
    }

    public void setFeed(News feed) {
        this.feed = feed;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public int getImage(){
        return image;
    }

    @Override
    public int compareTo(Card card){
        if(isFeed){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = sdf.parse(getDate());
                Date date2 = sdf.parse(card.getDate());
                if (date1.after(date2)) {
                    return -1;
                }else{
                    return 1;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = sdf.parse(ticket.getDate());
                Date date2 = sdf.parse(card.getTicket().getDate());
                if(ticket.getIsValidated())
                    return 1;
                else if(card.getTicket().getIsValidated())
                    return -1;
                if (date1.after(date2)) {
                    return -1;
                }else{
                    return 1;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }
}

