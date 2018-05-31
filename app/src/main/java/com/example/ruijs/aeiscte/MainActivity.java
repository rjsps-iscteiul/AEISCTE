package com.example.ruijs.aeiscte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ruijs.aeiscte.fragments.AboutFragment;
import com.example.ruijs.aeiscte.fragments.ContactsFragment;
import com.example.ruijs.aeiscte.fragments.EventsListFragment;
import com.example.ruijs.aeiscte.fragments.FeedListFragment;
import com.example.ruijs.aeiscte.fragments.ProfileFragment;
import com.example.ruijs.aeiscte.fragments.SettingsFragment;
import com.example.ruijs.aeiscte.fragments.SocialFragment;
import com.example.ruijs.aeiscte.fragments.TicketsListFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FeedListFragment fragFeed = new FeedListFragment();
    EventsListFragment fragEvents = new EventsListFragment();
    ProfileFragment fragProfile = new ProfileFragment();
    ContactsFragment fragContacts = new ContactsFragment();
    SocialFragment fragSocial = new SocialFragment();
    AboutFragment fragAbout = new AboutFragment();
    TicketsListFragment fragTickets = new TicketsListFragment();
    SettingsFragment fragSettings = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("CONA", "AQUI");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Log.d("CONA", "TAAAAAA");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Create Feed
        FeedFactory.createFeed();

        //Read Database


        // Default Fragment
        Fragment fragment = new FeedListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.screen_area, fragment);
        fragmentTransaction.commit();

        navigationView.setCheckedItem(R.id.menu_feed);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.menu_feed) {

            fragment = fragFeed;

        } else if (id == R.id.menu_eventos) {

            fragment = fragEvents;

        } else if (id == R.id.menu_perfil) {

            fragment = fragProfile;

        } else if (id == R.id.menu_tickets) {

            fragment = fragTickets;

        } else if (id == R.id.menu_contactos) {

            fragment = fragContacts;

        } else if (id == R.id.menu_socials) {

            fragment = fragSocial;

        } else if (id == R.id.menu_sobre) {

            fragment =fragAbout;

        } else if (id == R.id.menu_settings) {

            fragment = fragSettings;

        } else if (id == R.id.menu_logout) {

            logoutUser();

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser(){

        FirebaseAuth.getInstance().signOut();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Toast.makeText(getApplicationContext(), R.string.logout_menu_no_success, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, LogIn.class));
            Toast.makeText(getApplicationContext(), R.string.logout_menu_success, Toast.LENGTH_SHORT).show();
            finish();
        }

    }

}
