package com.example.ruijs.aeiscte.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ruijs.aeiscte.CardAdapter;
import com.example.ruijs.aeiscte.MainActivity;
import com.example.ruijs.aeiscte.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

import com.example.ruijs.aeiscte.R;

public class ProfileFragment extends Fragment {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Users");

    boolean is_editing = false;
    EditText et_user_name, et_curso , et_email, et_birth , et_phone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_profile, null);

        et_user_name = myView.findViewById(R.id.user_name);
        et_curso = myView.findViewById(R.id.curso_text);
        et_email = myView.findViewById(R.id.email_text);
        et_birth = myView.findViewById(R.id.birth_text);
        et_phone = myView.findViewById(R.id.phone_text);
        et_email.setText(user.getEmail());

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User userDS;
                    TextView tv_user_name = ((MainActivity) getActivity()).findViewById(R.id.headerUserName);
                    if(ds.getValue(User.class).getEmail().equals(user.getEmail())){
                        userDS = ds.getValue(User.class);
                        et_user_name.setText(userDS.getUser_name());
                        et_email.setText(userDS.getEmail());
                        et_birth.setText(userDS.getBirth());
                        if(userDS.getPhone()!=0) {
                            et_phone.setText(String.valueOf(userDS.getPhone()));
                        }
                        et_curso.setText(userDS.getCurso());
                        tv_user_name.setText(et_user_name.getText().toString());
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return myView;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final Button btn = (Button) (getActivity().findViewById(R.id.btn_edit_profile));
        getActivity().findViewById(R.id.btn_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EDITAR INFO
                if(is_editing == false){
                    //et_email.setEnabled(true);
                    et_curso.setFocusableInTouchMode(true);
                    et_curso.setFocusable(true);
                    et_user_name.setFocusableInTouchMode(true);
                    et_user_name.setFocusable(true);
                    et_email.setFocusableInTouchMode(true);
                    et_email.setFocusable(true);
                    et_birth.setFocusableInTouchMode(true);
                    et_birth.setFocusable(true);
                    et_phone.setFocusableInTouchMode(true);
                    et_phone.setFocusable(true);
                    is_editing = true;

                    btn.setText("Guardar Alterações");
                //GUARDAR INFO
                }else {
                    et_curso.setFocusableInTouchMode(false);
                    et_curso.setFocusable(false);
                    et_user_name.setFocusableInTouchMode(false);
                    et_user_name.setFocusable(false);
                    et_email.setFocusableInTouchMode(false);
                    et_email.setFocusable(false);
                    et_phone.setFocusableInTouchMode(false);
                    et_phone.setFocusable(false);
                    is_editing = false;
                    editUser();

                    btn.setText("Editar Perfil");
                }
            }
        });

    }

    public void editUser(){
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User userDS;
                    TextView tv_user_name = ((MainActivity) getActivity()).findViewById(R.id.headerUserName);

                    if(ds.getValue(User.class).getEmail().equals(user.getEmail())){
                        userDS = ds.getValue(User.class);
                        userDS.setUser_name(et_user_name.getText().toString());
                        userDS.setEmail(et_email.getText().toString());
                        userDS.setBirth(et_birth.getText().toString());
                        userDS.setPhone(Integer.parseInt(et_phone.getText().toString()));
                        userDS.setCurso(et_curso.getText().toString());

                        tv_user_name.setText(et_user_name.getText().toString());

                        databaseReference.child(user.getUid()).setValue(userDS);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
