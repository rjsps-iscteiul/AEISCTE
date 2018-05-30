package com.example.ruijs.aeiscte;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

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
                    is_editing = true;
                    //et_user_name.setFocusable(true);
                    //et_user_name.setFocusableInTouchMode(true);
                    //et_user_name.setClickable(true);
                    btn.setText("Guardar Alterações");
                //GUARDAR INFO
                }else {
                    is_editing = false;
                    final User utilizador = new User(et_user_name.getText().toString(), et_email.getText().toString(), et_curso.getText().toString(), et_birth.getText().toString(), Integer.parseInt(et_phone.getText().toString()));
                    inserirUser(utilizador);
                    //et_user_name.setEnabled(false);
                    //et_curso.enable setEnabled(false);
                    //et_email.setEnabled(false);
                    //et_birth.setEnabled(false);
                    //et_phone.setEnabled(false);

                    btn.setText("Editar Perfil");
                }
            }
        });

    }

    public void inserirUser(User user1){
        //final User user1 = new User(et_user_name.getText().toString(), et_email.getText().toString(), et_curso.getText().toString(), et_email.getText().toString(), Integer.parseInt(et_phone.getText().toString()));
        //databaseReference.child(user.getEmail()).addValueEventListener(new ValueEventListener() {
            //@Override
            //public void onDataChange(DataSnapshot dataSnapshot) {
                //if(user.getEmail().equals(dataSnapshot.getKey())){ //CASO EXISTA ESTA CHAVE NA BASE DE DADOS
                    databaseReference.child(createKey(user1.getEmail())).setValue(user1);
                //}
            //}

            //@Override
            //public void onCancelled(DatabaseError databaseError) {

            //}
        //});
    }

    public String createKey(String email){
        String [] email1 = email.split("@");
        return email1[0];
    }

    /*

        **********************************
        CODE WILL BE HERE AND ON THE ONCREATED METHODS IF NECESSARY
        **********************************

     */

}
