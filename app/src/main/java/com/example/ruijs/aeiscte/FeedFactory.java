package com.example.ruijs.aeiscte;

import android.util.Log;

import com.example.ruijs.aeiscte.objects.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedFactory {

    public static void createFeed(){
        //DataBase Feed
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Feed");

        News feed1 = new News("Festa do Caloiro", "Festas", "29/10/2017", 1, true, true, "1");
        //Feed feed2 = new Feed("Arraial do ISCTE", "Festas", "8/6/2018", 2, true, true, "2");
        //Feed feed3 = new Feed("Habemos NET!", "Núcleos", "24/5/2018", 3, false, false, "3");
        //Feed feed4 = new Feed("Sèrgio é pai", "ETI", "28/5/2018", 4, false, false, "4");

        Log.d("sdadas",databaseReference.toString());
        //databaseReference.child(feed1.getId()).setValue(feed1);
        //databaseReference.child(feed2.getId()).setValue(feed2);
        //databaseReference.child(feed3.getId()).setValue(feed3);
        //databaseReference.child(feed4.getId()).setValue(feed4);

    }

    public static void newCardFromDatabase(DataSnapshot ds){

    }
}
