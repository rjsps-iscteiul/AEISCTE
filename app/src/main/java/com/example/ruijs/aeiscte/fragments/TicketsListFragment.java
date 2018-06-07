package com.example.ruijs.aeiscte.fragments;

import android.content.Context;
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
import com.example.ruijs.aeiscte.objects.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TicketsListFragment extends Fragment{

    private View view;
    private CardAdapter adapter;
    private Context context;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



    ArrayList<Card> listOfCards = new ArrayList<Card>();
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getContext();

        view = inflater.inflate(R.layout.fragment_feed, container, false);
        final ListView listView = (view.findViewById(R.id.listView));

        listOfCards.clear();

        // ESTA FUNÇÃO É CHAMADA SEMPRE QUE ACEDEMOS À VIEW, PORTANTO CUIDADO COM ALGUMAS MERDAS

        //addCard("Festa do Caloiro","Ticket","01/01/2001",false,false,"148651");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Tickets");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOfCards.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Ticket ticket = ds.getValue(Ticket.class);
                    if(ticket.getUserId().equals(user.getUid())) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date strDate = sdf.parse(ticket.getDate());
                            if (!(new Date().after(strDate))) {
                                String valido = getString(R.string.card_available);
                                if(ticket.getIsValidated())
                                    valido=getString(R.string.card_used);
                                Card card = FeedFactory.ticketToCard(ticket, valido);
                                listOfCards.add(card);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Collections.sort(listOfCards);
                adapter = new CardAdapter(context,listOfCards);
                listView.setAdapter(adapter);

                if(listOfCards.size() == 0)
                    view.findViewById(R.id.no_tickets_textview).setVisibility(View.VISIBLE);
                else
                    view.findViewById(R.id.no_tickets_textview).setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new CardAdapter(context,listOfCards);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TicketFragment new_frag = new TicketFragment();
                new_frag.associateToTicket(listOfCards.get(position).getTicket());

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("Tickets");

                Ticket ticket = new_frag.getTicket();
                databaseReference.child(ticket.getTicketId()).setValue(ticket);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.screen_area, new_frag).addToBackStack(this.toString());
                fragmentTransaction.commit();
            }
        });

        view.findViewById(R.id.no_tickets_textview).setVisibility(View.VISIBLE);

        return view;
    }


    /*public void addCard(String name, String category, String date, boolean isEvent, boolean hasTicket, String id){
        Card newcard = new Card(name,category,date,R.drawable.ticket,isEvent,hasTicket,id);
        listOfCards.add(newcard);
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }*/
}
