package com.example.ruijs.aeiscte;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class MenuActivity extends AppCompatActivity implements
        InfoFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener
{

    FragmentManager fragmentManager = getSupportFragmentManager();

    // fragments saved to avoid resets
    private Fragment info_frag = new InfoFragment();

    // testing TAG
    private String TAG = "nossa_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        NavigationView navView = (NavigationView) findViewById(R.id.navigationView);
        navView.setNavigationItemSelectedListener(this);
    }

    protected void showInfo(){
        fragmentManager.beginTransaction().replace(R.id.fragment_container, info_frag).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG,"U triggered fragment interaction");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "li ->>> " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.db:
                Log.d(TAG, "CLICASTE NO FEED");
                return true;
            case R.id.event:
                Log.d(TAG, "CLICASTE NO EVENTS");
                return true;
            case R.id.settings:
                Log.d(TAG, "CLICASTE NO SETTINGS");
                showInfo();
                return true;
            case R.id.logout:
                Log.d(TAG, "CLICASTE NO LOGOUT");
                return true;
        }
        return false;

    }
}
