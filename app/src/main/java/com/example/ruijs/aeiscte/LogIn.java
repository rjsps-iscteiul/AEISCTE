package com.example.ruijs.aeiscte;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    final String LOG_TAG = "LogInActivity";

    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextEmail = (EditText) findViewById(R.id.input_email);
        editTextPassword = (EditText) findViewById(R.id.input_password);

        findViewById(R.id.link_signup).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Email validations
        if(email.isEmpty()){
            editTextEmail.setError(getString(R.string.login_email_missing));
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError(getString(R.string.login_email_wrong));
            editTextEmail.requestFocus();
            return;
        }

        // Password validations
        if(password.isEmpty()){
            editTextPassword.setError(getString(R.string.login_pass_missing));
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    // Sign in success
                    Log.d(LOG_TAG, "signInWithEmail:success");

                    // Go to main activity
                    Intent intent = new Intent(LogIn.this, InfoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {

                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        // If Wrong Password
                        Toast.makeText(getApplicationContext(), getString(R.string.login_signin_wrong_pass), Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(LOG_TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), getString(R.string.login_signin_error), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.link_signup:

                startActivity(new Intent(this, SignUp.class));
                break;

            case R.id.btn_login:

                userLogin();
                break;



        }
    }

}
