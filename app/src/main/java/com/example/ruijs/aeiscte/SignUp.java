package com.example.ruijs.aeiscte;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUserName, editTextEmail, editTextPassword;

    final String LOG_TAG = "SignUpActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //GFJDF
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUserName = (EditText) findViewById(R.id.input_user);
        editTextEmail = (EditText) findViewById(R.id.input_email);
        editTextPassword = (EditText) findViewById(R.id.input_password);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        findViewById(R.id.btn_signup).setOnClickListener(this);
        findViewById(R.id.link_login).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void registerUser() {

        String userName = editTextUserName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // User name validations
        if(userName.isEmpty()){
            editTextUserName.setError(getString(R.string.signup_username_missing));
            editTextUserName.requestFocus();
            return;
        }

        // Email validations
        if(email.isEmpty()){
            editTextEmail.setError(getString(R.string.signup_email_missing));
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError(getString(R.string.signup_email_wrong));
            editTextEmail.requestFocus();
            return;
        }

        // Password validations
        if(password.isEmpty()){
            editTextPassword.setError(getString(R.string.signup_password_missing));
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError(getString(R.string.signup_password_wrong));
            editTextPassword.requestFocus();
            return;
        }

        // Firebase method to authenticate with email and password
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    // Update user name of the person who has just registered
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userName = editTextUserName.getText().toString().trim();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(userName)
                            .build();
                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(LOG_TAG, "User profile updated.");
                                    }
                                }
                            });

                    User userDB = new User(userName, email);
                    databaseReference.child(user.getUid()).setValue(userDB);

                    // Sign in success
                    Toast.makeText(getApplicationContext(), getString(R.string.signup_register_sucessfull), Toast.LENGTH_SHORT).show();
                    Log.d(LOG_TAG, "createUserWithEmail:success");



                    // Timer to wait 1 second just to the user see that registered successfully
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                startActivity(new Intent(getApplicationContext(), LogIn.class));
                                finish();
                            }
                        }
                    };
                    timer.start();

                } else {

                    // If sign in fails, display a message to the user
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), getString(R.string.signup_register_collision), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.signup_register_error), Toast.LENGTH_SHORT).show();
                        Log.w(LOG_TAG, "createUserWithEmail:failure", task.getException());
                    }

                }
            }
        });


    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_signup:
                registerUser();
                break;

            case R.id.link_login:
                startActivity(new Intent(this, LogIn.class));
                break;
        }
    }
}
