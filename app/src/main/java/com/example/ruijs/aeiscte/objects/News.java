package com.example.ruijs.aeiscte.objects;

import com.example.ruijs.aeiscte.Card;

public class News {

    private String name;
    private String category;
    private String date;
    private String eventDate;
    private String text;
    private String local;
    private int image;
    private boolean isEvent;
    private boolean hasTicket;
    private String id;

    public News(){

    }

    public News(String name, String category, String date, int image, boolean isEvent, boolean hasTicket, String id){

        this.name = name;
        this.category = category;
        this.date = date;
        this.image = image;
        this.isEvent = isEvent;
        this.hasTicket = hasTicket;
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getIsEvent() {
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

    public boolean getHasTicket(){
        return hasTicket;
    }

    public String getId() {
        return id;
    }

    public int getImage(){
        return image;
    }

    /*public Card getCard(){
        Card card = new Card(name, category, date, image);
        card.setFeed(this);
        return card;
    }*/

    @Override
    public String toString() {
        return getName();
    }
}
