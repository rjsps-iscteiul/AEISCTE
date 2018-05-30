package com.example.ruijs.aeiscte;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedListFragment extends Fragment{

    private View view;
    CardAdapter adapter;

    ArrayList<Card> listOfCards = new ArrayList<Card>();
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // DEVE CARREGAR OS CARDS DA BASE DE DADOS NO ON CREATE ANTES DE INICIAR O ADAPTER
        // MERDAS PARA LER NA BASE DE DADOS ____name_categoria_data_isEvent_etc_etc_etc__________ INCLUINDO SE É OU NÃO FEED/EVENTO COM BILHETES
        // ESTA FUNÇÃO É CHAMADA SEMPRE QUE ACEDEMOS À VIEW, PORTANTO CUIDADO COM ALGUMAS MERDAS

        //listOfCards.clear();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Feed");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Card card = ds.getValue(Card.class);
                    //Card card = FeedFactory.newCardFromDatabase(ds);
                    listOfCards.add(card);
                }
                //adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("ADEUS", "TAMANHO "+listOfCards.size());

        //for(int i = 0; i < 6; i++)
        //    addCard("FEED_N_"+i,"FEED_N_"+i,"0"+i+"/01/01",false,false,"a"+i+"aKMNNMNMNNMNdn"+i*2183+"lkqnd");
        view = inflater.inflate(R.layout.fragment_feed, container, false);

        adapter = new CardAdapter(this.getContext(),listOfCards);
        final ListView listView = (view.findViewById(R.id.listView));
        listView.setAdapter(adapter);
        listView.setClickable(true);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FeedEventFragment new_frag = new FeedEventFragment();
                new_frag.associateToCard(listOfCards.get(position));

                // ETC ETC ETC ETC ETC




                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, new_frag).addToBackStack(this.toString());
                fragmentTransaction.commit();
            }
        });
        view.findViewById(R.id.no_tickets_textview).setVisibility(View.INVISIBLE);

        return view;
    }


   /* public void addCard(String name, String category, String date, boolean isEvent, boolean hasTicket, String id){
        final Card newcard;

        if(isEvent)
            newcard = new Card(name,category,date,R.drawable.event,isEvent,hasTicket,id);
        else
            newcard = new Card(name,category,date,R.drawable.news,isEvent,hasTicket,id);

        listOfCards.add(newcard);
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }*/
}
