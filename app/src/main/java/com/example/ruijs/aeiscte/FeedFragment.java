package com.example.ruijs.aeiscte;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FeedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, null);

        LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.feed_linearLayout);


        //Depois é preciso saber quantas noticias estão na base de dados e criar um for para as primeiras 20 (por exemplo)
        //Podiamos ver se, a pessoa vai muito para baixo é que carrega mais noticias
        Fragment card = new Card();
        getFragmentManager().beginTransaction().add(R.id.feed_linearLayout, new Card()).commit();
        getFragmentManager().beginTransaction().add(R.id.feed_linearLayout, new Card()).commit();
        getFragmentManager().beginTransaction().add(R.id.feed_linearLayout, new Card()).commit();

        return view;
    }

}
