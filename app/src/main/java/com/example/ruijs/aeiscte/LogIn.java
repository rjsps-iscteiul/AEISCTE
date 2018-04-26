package com.example.ruijs.aeiscte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.link_signup).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.link_signup:

                startActivity(new Intent(this, SignUp.class));

                break;

            case R.id.btn_login:

                // Fazer verificações das cenas de login

                startActivity(new Intent(this, MainActivity.class));
                
                break;



        }
    }

}
