package com.example.ruijs.aeiscte;

        import android.media.Image;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;

        import java.util.Date;

public class EventsFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, null);

        LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(R.id.event_linearLayout);


        //Depois é preciso saber quantos eventos estão na base de dados e criar um for para os primeiros 20 (por exemplo)
        //Podiamos ver se, a pessoa vai muito para baixo é que carrega mais eventos
        Fragment card = new Card();
        getFragmentManager().beginTransaction().add(R.id.event_linearLayout, new Card()).commit();
        getFragmentManager().beginTransaction().add(R.id.event_linearLayout, new Card()).commit();
        getFragmentManager().beginTransaction().add(R.id.event_linearLayout, new Card()).commit();

        return view;
    }

    @Override
    public void onClick(View view){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.screen_area, new FeedAbout());
        fragmentTransaction.commit();
    }

    public void addCard(String name, String category, String date){
        Card newcard = new Card();
        newcard.setName(name);
        newcard.setCategory(category);
        newcard.setDate(date);

        getFragmentManager().beginTransaction().add(R.id.event_linearLayout,newcard).commit();
    }
}
