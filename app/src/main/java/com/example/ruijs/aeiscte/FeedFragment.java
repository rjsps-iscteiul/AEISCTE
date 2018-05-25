package com.example.ruijs.aeiscte;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FeedFragment extends Fragment{

    private View view;
    FeedAdapter adapter;

    ArrayList<Card> listOfCards = new ArrayList<Card>();
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // DEVE CARREGAR OS CARDS DA BASE DE DADOS NO ON CREATE ANTES DE INICIAR O ADAPTER

        for(int i = 0; i < 6; i++)
            addCard("TÍTULO_N_"+i,"CATEGORIA_N_"+i,"0"+i+"/01/01");
        view = inflater.inflate(R.layout.fragment_feed, container, false);

        adapter = new  FeedAdapter(this.getContext(),listOfCards);
        final ListView listView = (view.findViewById(R.id.listView));
        listView.setAdapter(adapter);
        listView.setClickable(true);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FeedAboutFragment new_frag = new FeedAboutFragment();
                new_frag.addTitle(listOfCards.get(position).getName());
                new_frag.addCategory(listOfCards.get(position).getCategory());
                new_frag.addDate(listOfCards.get(position).getDate());
                // ETC ETC ETC ETC ETC




                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, new_frag);
                fragmentTransaction.commit();
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
