package com.example.ruijs.aeiscte;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedAboutFragment extends Fragment {

    private View view;
    private String name,category,date;
    private boolean isEvent = false;

    public FeedAboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feed_about, container, false);
        ((TextView)view.findViewById(R.id.feed_title_title)).setText(name);

        ((TextView)view.findViewById(R.id.feed_category_title)).setText(category);
        ((TextView)view.findViewById(R.id.feed_date_post)).setText(date);

        if(isEvent)
            ((ImageButton)view.findViewById(R.id.btn_ticket)).setVisibility(View.VISIBLE);
        else
            ((ImageButton)view.findViewById(R.id.btn_ticket)).setVisibility(View.INVISIBLE);


        return view;
    }

    public void addTitle(String name) {
        this.name = name;
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
}
