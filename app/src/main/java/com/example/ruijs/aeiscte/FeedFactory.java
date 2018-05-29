package com.example.ruijs.aeiscte;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;

public class FeedFactory {

    public static void createFeed(){
        //DataBase Feed
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Feed");

        Card card1 = new Card("Festa do Caloiro", "Festas", "29/10/2017", 1, true, true, "1");
        Card card2 = new Card("Arraial do ISCTE", "Festas", "8/6/2018", 2, true, true, "2");
        Card card3 = new Card("Habemos NET!", "Núcleos", "24/5/2018", 3, false, false, "3");
        Card card4 = new Card("Sèrgio é pai", "ETI", "28/5/2018", 4, false, false, "4");

        databaseReference.child(card1.getId()).setValue(card1);
        databaseReference.child(card2.getId()).setValue(card2);
        databaseReference.child(card3.getId()).setValue(card3);
        databaseReference.child(card4.getId()).setValue(card4);
    }

    public static void newCardFromDatabase(DataSnapshot ds){

    }
}
