package com.example.ruijs.aeiscte;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class EventsFragment extends Fragment{

    private View view;
    FeedAdapter adapter;

    ArrayList<Card> listOfCards = new ArrayList<Card>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // DEVE CARREGAR OS CARDS DA BASE DE DADOS NO ON CREATE ANTES DE INICIAR O ADAPTER

        for(int i = 0; i < 6; i++)
            addCard("EVENT_N_"+i,"EVENT_N_"+i,"0"+i+"/01/01");
        view = inflater.inflate(R.layout.fragment_feed, container, false);

        adapter = new  FeedAdapter(this.getContext(),listOfCards);
        final ListView listView = (view.findViewById(R.id.listView));
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getBaseContext(), "TOU AQUI no eventooo", Toast.LENGTH_SHORT).show();
                Log.d("WORKED","eventooooo");
            }
        });
        return view;
    }
    public void addCard(String name, String category, String date){
        Card newcard = new Card();
        newcard.setName(name);
        newcard.setCategory(category);
        newcard.setDate(date);
        listOfCards.add(newcard);
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }
}
