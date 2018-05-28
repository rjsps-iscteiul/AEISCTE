package com.example.ruijs.aeiscte;


import android.media.Image;


public class Card{

    private String name="TÍTULO";
    private String category="CATEGORIA";
    private String date="DATA";
    private Image image;
    private boolean isEvent;
    private String id;

    public Card(String name, String category, String date, Image image, boolean isEvent, String id){

        this.name = name;
        this.category = category;
        this.date = date;
        this.image = image;
        this.isEvent = isEvent;
        this.id = id;
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

    public boolean isEvent(){
        return isEvent;
    }

    public String getId() { return id; }

    /*public Image getImage() {
        return image;
    }                                       TEMOS QUE REVER ESTE PORQUE AS IMAGENS TEM QUE EXISTIR NOS DRAWABLES

    */

   }