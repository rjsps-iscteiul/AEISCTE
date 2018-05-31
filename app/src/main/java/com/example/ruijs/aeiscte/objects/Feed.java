package com.example.ruijs.aeiscte.objects;

import com.example.ruijs.aeiscte.Card;

public class Feed {

    private String name="TÍTULO";
    private String category="CATEGORIA";
    private String date="DATA";
    private int image;
    private boolean isEvent;
    private boolean hasTicket;
    private String id;

    public Feed(){

    }

    public Feed(String name, String category, String date, int image, boolean isEvent, boolean hasTicket, String id){

        this.name = name;
        this.category = category;
        this.date = date;
        this.image = image;
        this.isEvent = isEvent;
        this.hasTicket = hasTicket;
        this.id = id;
    }

    public boolean isHasTicket() {
        return hasTicket;
    }

    public boolean isEvent() {
        return isEvent;
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

    public boolean hasTicket(){
        return hasTicket;
    }

    public String getId() {
        return id;
    }

    public int getImage(){
        return image;
    }

    public Card getCard(){
        Card card = new Card(name, category, date, image);
        card.setFeed(this);
        return card;
    }

}
