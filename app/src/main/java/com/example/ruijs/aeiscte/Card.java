package com.example.ruijs.aeiscte;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Card{

    private String name="TÍTULO";
    private String category="CATEGORIA";
    private String date="DATA";
    private Image image;
    private boolean isEvent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAsEvent(){
        isEvent = true;
    }

    public boolean isEvent(){
        return isEvent;
    }

    /*public Image getImage() {
        return image;
    }                                       TEMOS QUE REVER ESTE PORQUE AS IMAGENS TEM QUE EXISTIR NOS DRAWABLES

    public void setImage(Drawable image) {
        this.image = image;
    }*/
}
