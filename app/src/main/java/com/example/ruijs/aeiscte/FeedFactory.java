package com.example.ruijs.aeiscte;

import android.util.Log;

import com.example.ruijs.aeiscte.objects.News;
import com.example.ruijs.aeiscte.objects.Ticket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedFactory {

    public static void createFeed(){
        //DataBase Feed
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Feed");

        News feed1 = new News("Festa do Caloiro", "Festas", "29/10/2017", R.drawable.event, true, true, "1");
        News feed2 = new News("Arraial do ISCTE", "Festas", "8/6/2018", R.drawable.event, true, true, "2");
        News feed3 = new News("Habemos NET!", "Núcleos", "24/5/2018", R.drawable.event, false, false, "3");
        News feed4 = new News("Sèrgio é pai", "ETI", "28/5/2018", R.drawable.event, false, false, "4");

        Log.d("sdadas",databaseReference.toString());
        databaseReference.child(feed1.getId()).setValue(feed1);
        databaseReference.child(feed2.getId()).setValue(feed2);
        databaseReference.child(feed3.getId()).setValue(feed3);
        databaseReference.child(feed4.getId()).setValue(feed4);

    }

    public static Card feedToCard(News feed){
        Card card = new Card(feed.getName(), feed.getCategory(), feed.getDate(), feed.getImage());
        card.setFeed(feed);
        return card;
    }

    public static Card ticketToCard(Ticket ticket){
        Card card = new Card(ticket.getName(), "Ticket", ticket.getDate(), R.drawable.ticket);
        card.setTicket(ticket);
        return card;
    }
}
