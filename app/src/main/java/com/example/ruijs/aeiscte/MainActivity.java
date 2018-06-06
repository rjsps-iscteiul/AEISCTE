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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruijs.aeiscte.fragments.AboutFragment;
import com.example.ruijs.aeiscte.fragments.ContactsFragment;
import com.example.ruijs.aeiscte.fragments.EventsListFragment;
import com.example.ruijs.aeiscte.fragments.FeedListFragment;
import com.example.ruijs.aeiscte.fragments.ProfileFragment;
import com.example.ruijs.aeiscte.fragments.ReaderFragment;
import com.example.ruijs.aeiscte.fragments.SettingsFragment;
import com.example.ruijs.aeiscte.fragments.SocialFragment;
import com.example.ruijs.aeiscte.fragments.TicketsListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
    ReaderFragment fragReader = new ReaderFragment();

    TextView tx_user_name, tx_email;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public String userLogged = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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

        //GONÇALO FEZ MERDA
        tx_user_name = findViewById(R.id.headerUserName);
        tx_email = findViewById(R.id.headerUserEmail);

        /*if(!user.getDisplayName().isEmpty()){
            tx_user_name.setText(user.getDisplayName());
        }
        tx_email.setText(user.getEmail().toString());*/

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

        } else if (id == R.id.menu_scanner) {

            fragment = fragReader;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("MEKIE", "entreiiii ");

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan cancelado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                Log.d("MEKIE", "SCAN DEU "+result.getContents());
                FeedFactory.confirmTicket(result.getContents(), this);
            }
        }else{
            Log.d("MEKIE", "FOI AQUI "+result.getContents());
            super.onActivityResult(requestCode, resultCode, data);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
