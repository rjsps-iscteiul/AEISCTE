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

public class TicketsListFragment extends Fragment{

    private View view;
    CardAdapter adapter;

    ArrayList<Card> listOfCards = new ArrayList<Card>();
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_feed, container, false);

        listOfCards.clear();

        // ESTA FUNÇÃO É CHAMADA SEMPRE QUE ACEDEMOS À VIEW, PORTANTO CUIDADO COM ALGUMAS MERDAS

        addCard("Festa do Caloiro","Ticket","01/01/2001",false,false,"");

        adapter = new CardAdapter(this.getContext(),listOfCards);
        final ListView listView = (view.findViewById(R.id.listView));
        listView.setAdapter(adapter);
        listView.setClickable(true);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TicketFragment new_frag = new TicketFragment();
                new_frag.associateToCard(listOfCards.get(position));

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, new_frag).addToBackStack(this.toString());
                fragmentTransaction.commit();
            }
        });

        if(listOfCards.size() == 0)
            view.findViewById(R.id.no_tickets_textview).setVisibility(View.VISIBLE);
        else
            view.findViewById(R.id.no_tickets_textview).setVisibility(View.INVISIBLE);

        return view;
    }


    public void addCard(String name, String category, String date, boolean isEvent, boolean hasTicket, String id){
        Card newcard = new Card(name,category,date,R.drawable.ticket,isEvent,hasTicket,id);
        listOfCards.add(newcard);
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }
}