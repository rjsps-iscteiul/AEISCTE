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
public class Card extends Fragment{

    private String name;
    private String category;
    private String date;
    private Image image;

    public Card() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cardView = inflater.inflate(R.layout.fragment_card, container, false);
        if(name != null)
            ((TextView) cardView.findViewById(R.id.news_name)).setText(name);
        if(category != null)
            ((TextView) cardView.findViewById(R.id.news_category)).setText(category);
        if(date != null)
            ((TextView) cardView.findViewById(R.id.news_date)).setText(date.toString());
        return cardView;
    }

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

    /*public Image getImage() {
        return image;
    }                                       TEMOS QUE REVER ESTE PORQUE AS IMAGENS TEM QUE EXISTIR NOS DRAWABLES

    public void setImage(Drawable image) {
        this.image = image;
    }*/
}
