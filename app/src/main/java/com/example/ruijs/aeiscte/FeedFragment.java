package com.example.ruijs.aeiscte;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class FeedFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, null);

        LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.feed_linearLayout);

        //Depois é preciso saber quantas noticias estão na base de dados e criar um for para as primeiras 20 (por exemplo)
        //Podiamos ver se, a pessoa vai muito para baixo é que carrega mais noticias
        for(int i = 0; i < 5; i++){ // testing purposes

                addCard("titulo_" + i,"testes", "01/01/01");

        }

        return view;
    }

    @Override
    public void onClick(View view){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.screen_area, new FeedAbout());
        fragmentTransaction.commit();
    }

    public void addCard(String name, String category, String date){
        Card newcard = new Card();
        newcard.setName(name);
        newcard.setCategory(category);
        newcard.setDate(date);

        getFragmentManager().beginTransaction().add(R.id.feed_linearLayout,newcard).commit();
    }
}
