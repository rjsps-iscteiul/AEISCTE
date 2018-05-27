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

public class TicketsFragment extends Fragment{

    private View view;
    TicketsAdapter adapter;

    ArrayList<Card> listOfCards = new ArrayList<Card>();
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // DEVE CARREGAR OS CARDS DA BASE DE DADOS NO ON CREATE ANTES DE INICIAR O ADAPTER
        // MERDAS PARA LER NA BASE DE DADOS ____name_categoria_data_isEvent_etc_etc_etc__________ INCLUINDO SE É OU NÃO FEED/EVENTO COM BILHETES


        for(int i = 0; i < 6; i++)
            addCard("BILHETE_N_"+i,"CATEGORIA_N_"+i,"0"+i+"/01/01");
        view = inflater.inflate(R.layout.fragment_tickets, container, false);

        adapter = new  TicketsAdapter(this.getContext(),listOfCards);
        final ListView listView = (view.findViewById(R.id.listViewTicket));
        listView.setAdapter(adapter);
        listView.setClickable(true);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ticket new_frag = new Ticket();
                new_frag.setEventId("eventId"); //É PRECISO METER UM ID PARA CADA EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
                // ETC ETC ETC ETC ETC




                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, new_frag).addToBackStack(this.toString());
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
