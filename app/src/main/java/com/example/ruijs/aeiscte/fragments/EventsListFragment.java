package com.example.ruijs.aeiscte.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ruijs.aeiscte.Card;
import com.example.ruijs.aeiscte.CardAdapter;
import com.example.ruijs.aeiscte.FeedFactory;
import com.example.ruijs.aeiscte.R;
import com.example.ruijs.aeiscte.objects.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class EventsListFragment extends Fragment{

    private View view;
    CardAdapter adapter;

    ArrayList<Card> listOfCards = new ArrayList<Card>();
    FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        listOfCards.clear();

        // DEVE CARREGAR OS CARDS DA BASE DE DADOS NO ON CREATE ANTES DE INICIAR O ADAPTER
        // MERDAS PARA LER NA BASE DE DADOS ____name_categoria_data_isEvent_id_etc_etc_etc__________ INCLUINDO SE É OU NÃO FEED/EVENTO COM BILHETES
        // ESTA FUNÇÃO É CHAMADA SEMPRE QUE ACEDEMOS À VIEW, PORTANTO CUIDADO COM ALGUMAS MERDAS

        //for(int i = 0; i < 6; i++)
        //int i=0;
            //listOfCards.add(new Card("EVENT_N_"+i,"EVENT_N_"+i,"0"+i+"/01/01",true,true,"a"+i+"akndklawnldn"+i*2183+"lkqnd"));

        //int i=0;
        //News news = new News("EVENT_N_"+i,"EVENT_N_"+i,"0"+i+"/01/01",1, true,true,"a"+i+"akndklawnldn"+i*2183+"lkqnd");

        listOfCards.clear();
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        final ListView listView = (view.findViewById(R.id.listView));

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Feed");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    News feed = ds.getValue(News.class);
                    if(feed.getIsEvent()) {
                        Card card = FeedFactory.feedToCard(feed);
                        Log.d("CONA", "OI "+feed.getImage());
                        listOfCards.add(card);
                    }
                }
                Collections.sort(listOfCards);
                adapter = new CardAdapter(getContext(),listOfCards);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter = new CardAdapter(this.getContext(),listOfCards);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FeedEventFragment new_frag = new FeedEventFragment();
                new_frag.associateToFeed(listOfCards.get(position).getFeed());

                // ETC ETC ETC ETC ETC




                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, new_frag).addToBackStack(this.toString());
                fragmentTransaction.commit();
            }
        });

        view.findViewById(R.id.no_tickets_textview).setVisibility(View.INVISIBLE);

        return view;
    }


    /*public void addCard(String name, String category, String date, boolean isEvent, boolean hasTicket, String id){
        Card newcard = new Card(name,category,date,R.drawable.event,isEvent,hasTicket,id);
        listOfCards.add(newcard);
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }*/
}
