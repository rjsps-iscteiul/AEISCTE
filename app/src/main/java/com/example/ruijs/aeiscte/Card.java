package com.example.ruijs.aeiscte;


import com.example.ruijs.aeiscte.objects.News;
import com.example.ruijs.aeiscte.objects.Ticket;

public class Card{

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
    public String toString() {
        return getName();
    }
}

