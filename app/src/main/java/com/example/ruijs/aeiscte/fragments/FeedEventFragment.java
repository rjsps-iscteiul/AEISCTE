package com.example.ruijs.aeiscte.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruijs.aeiscte.R;
import com.example.ruijs.aeiscte.objects.News;
import com.example.ruijs.aeiscte.objects.Ticket;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FeedEventFragment extends Fragment {

    private View view;
    private String name,category,date, eventId;
    private boolean isEvent,hasTicket;
    private FragmentManager fragmentManager;
    private News feed;

    public FeedEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feed_event, container, false);
        ((TextView)view.findViewById(R.id.feed_title_title)).setText(name);

        ((TextView)view.findViewById(R.id.feed_category_title)).setText(category);
        ((TextView)view.findViewById(R.id.feed_date_post)).setText(date);

        fragmentManager = this.getActivity().getSupportFragmentManager();

        if(hasTicket){
            FloatingActionButton imageButton = view.findViewById(R.id.btn_ticket);
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TicketFragment ticket = new TicketFragment();
                    //ticket.setEventId(eventId);

                    new AlertDialog.Builder(getContext())
                            .setTitle("Ticket Acquisition")
                            .setMessage("Do you want to buy a ticket to " + name + " ?")
                            .setIcon(android.R.drawable.ic_dialog_dialer)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference = firebaseDatabase.getReference("Tickets");

                                    Ticket ticket = new Ticket(eventId);
                                    databaseReference.child(ticket.getTicketId()).setValue(ticket);

                                    Toast.makeText(getContext(), "Ticket Acquired to " + name, Toast.LENGTH_SHORT).show();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });

        }else
            (view.findViewById(R.id.btn_ticket)).setVisibility(View.INVISIBLE);


        return view;
    }

    public void addTitle(String name) {
        this.name = name;
    }

    public void addId(String eventId) {
        this.eventId = eventId;
    }

    public void addCategory(String category) {
        this.category = category;
    }

    public void addDate(String date) {
        this.date = date;
    }

    public void isEvent(boolean isornot) {
        this.isEvent = isornot;
    }

    public void setHasTicket(boolean haveornot) { this.hasTicket = haveornot;}

    public String getEventId(){
        return eventId;
    }

    public void associateToFeed(News feed){
        this.feed = feed;
        addTitle(feed.getName());
        addCategory(feed.getCategory());
        addDate(feed.getDate());
        isEvent(feed.getIsEvent());
        setHasTicket(feed.getHasTicket());
        addId(feed.getId());
    }
}
