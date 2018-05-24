package com.example.ruijs.aeiscte;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

public class ProfileFragment extends Fragment {

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    DatabaseReference myRef = database.getReference("message");
    boolean is_editing = false;
    EditText et_user_name, et_curso , et_email, et_birth , et_phone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        et_user_name = getActivity().findViewById(R.id.user_name);
        et_curso = getActivity().findViewById(R.id.curso_text);
        et_email = getActivity().findViewById(R.id.email_text);
        et_birth = getActivity().findViewById(R.id.birth_text);
        et_phone = getActivity().findViewById(R.id.phone_text);

        return inflater.inflate(R.layout.fragment_profile, null);
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
                    btn.setText("Guardar Alterações");
                //GUARDAR INFO
                }else {
                    is_editing = false;
                    btn.setText("Editar Perfil");
                }


            }
        });

    }

    /*

        **********************************
        CODE WILL BE HERE AND ON THE ONCREATED METHODS IF NECESSARY
        **********************************

     */

}
