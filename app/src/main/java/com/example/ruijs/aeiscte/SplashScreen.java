package com.example.ruijs.aeiscte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    // should go to shared preferences , TODO
    boolean logedIn = false;

    Animation fromBottomAnim;
    Animation scaleAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        tv = (TextView) findViewById(R.id.crText);
        iv = (ImageView) findViewById(R.id.aeLogo);

        fromBottomAnim = AnimationUtils.loadAnimation(this, R.anim.splashtransition);
        scaleAnim = AnimationUtils.loadAnimation(this, R.anim.splashscale);

        tv.startAnimation(fromBottomAnim);
        iv.startAnimation(scaleAnim);

        final Intent log_in_intent = new Intent(this, LogIn.class);
        //final Intent main_activity_intent = new Intent(this, News.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // if already have log in go to news page, if not, go to log in page
                    if(logedIn){
                        //startActivity(main_activity_intent);
                        finish();
                    } else {
                        startActivity(log_in_intent);
                        finish();
                    }
                }
            }
        };

        timer.start();
    }
}
