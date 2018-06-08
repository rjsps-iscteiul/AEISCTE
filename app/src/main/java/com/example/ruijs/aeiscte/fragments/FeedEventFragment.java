package com.example.ruijs.aeiscte.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruijs.aeiscte.R;
import com.example.ruijs.aeiscte.objects.News;
import com.example.ruijs.aeiscte.objects.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        ((TextView)view.findViewById(R.id.feed_text_title)).setText(getString(R.string.feedEvent_feed));
        ((TextView)view.findViewById(R.id.feed_text)).setText(feed.getText());
        if(feed.getEventDate()!=null) {
            ((TextView) view.findViewById(R.id.feed_date_title)).setText(getString(R.string.feedEvent_when));
            ((TextView) view.findViewById(R.id.feed_date)).setText(feed.getEventDate());
        }else{
            view.findViewById(R.id.feed_date_title).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.feed_date).setVisibility(View.INVISIBLE);
        }
        if(feed.getLocal()!=null) {
            ((TextView) view.findViewById(R.id.feed_local_title)).setText(getString(R.string.feedEvent_where));
            ((TextView)view.findViewById(R.id.feed_local)).setText(feed.getLocal());
        }else{
            view.findViewById(R.id.feed_local_title).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.feed_local).setVisibility(View.INVISIBLE);
        }
        if(feed.getHasTicket()) {
            ((TextView) view.findViewById(R.id.feed_price_title)).setText(getString(R.string.feedEvent_price));
            ((TextView)view.findViewById(R.id.feed_price)).setText(feed.getPrice()+" €");
        }else{
            view.findViewById(R.id.feed_price_title).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.feed_price).setVisibility(View.INVISIBLE);
        }

        fragmentManager = this.getActivity().getSupportFragmentManager();
        Log.d("CONA", "OMMMMM");

        if(hasTicket){
            try {
                FloatingActionButton imageButton = view.findViewById(R.id.btn_ticket);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date strDate = sdf.parse(feed.getEventDate());
                Date today = sdf.parse(sdf.format(new Date()));
                if (today.before(strDate) || today.equals(strDate)) {
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TicketFragment ticket = new TicketFragment();
                            //ticket.setEventId(eventId);

                            new AlertDialog.Builder(getContext())
                                    .setTitle(getString(R.string.feedEvent_ticket_aq))
                                    .setMessage(getString(R.string.feedEvent_ticket_want) + " " + name + " ?")
                                    .setIcon(android.R.drawable.ic_dialog_dialer)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                            DatabaseReference databaseReference = firebaseDatabase.getReference("Tickets");

                                            Ticket ticket = new Ticket(eventId);
                                            ticket.setName(name);
                                            ticket.setDate(feed.getEventDate());

                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            ticket.setUserId(user.getUid());

                                            databaseReference.child(ticket.getTicketId()).setValue(ticket);

                                            Toast.makeText(getContext(), getString(R.string.feedEvent_ticket_new) + " " + name, Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null).show();
                        }
                    });
                }else{
                    imageButton.setVisibility(View.INVISIBLE);
                }
            }catch (ParseException | IllegalStateException e){
                e.printStackTrace();
            }

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
