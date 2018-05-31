package com.example.ruijs.aeiscte;

import android.util.Log;

import com.example.ruijs.aeiscte.objects.Feed;
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
        Log.d("CONA", "11111111");

        /*Feed feed1 = new Feed("Festa do Caloiro", "Festas", "29/10/2017", 1, true, true, "1");
        Log.d("CONA", "333333333333333");
        Feed feed2 = new Feed("Arraial do ISCTE", "Festas", "8/6/2018", 2, true, true, "2");
        Feed feed3 = new Feed("Habemos NET!", "Núcleos", "24/5/2018", 3, false, false, "3");
        Feed feed4 = new Feed("Sèrgio é pai", "ETI", "28/5/2018", 4, false, false, "4");
*/
        Card card = new Card("a", "b", "c", 1);

        //Log.d("CONA", "555555555555555555 "+feed1);
        //databaseReference.child(card.getName()).setValue(card);
        Log.d("CONA", "44444444444");
        /*databaseReference.child(feed2.getId()).setValue(feed2);
        databaseReference.child(feed3.getId()).setValue(feed3);
        databaseReference.child(feed4.getId()).setValue(feed4);
        */Log.d("CONA", "22222222222");

    }

    public static void newCardFromDatabase(DataSnapshot ds){

    }
}
