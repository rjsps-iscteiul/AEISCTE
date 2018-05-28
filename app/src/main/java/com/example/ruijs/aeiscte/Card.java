package com.example.ruijs.aeiscte;



public class Card{

    private String name="TÍTULO";
    private String category="CATEGORIA";
    private String date="DATA";
    private int image;
    private boolean isEvent;
    private String id;

    public Card(String name, String category, String date, int image, boolean isEvent, String id){

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

    public String getId() {
        return id;
    }

    public int getImage(){
        return image;
    }

   }

