package com.example.final_p.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_p.R;
import com.example.final_p.classes.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link first#newInstance} factory method to
 * create an instance of this fragment.
 */
public class first extends Fragment {
    private FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public first() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment first.
     */
    // TODO: Rename and change types and number of parameters
    public static first newInstance(String param1, String param2) {
        first fragment = new first();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first, container, false);

        EditText useredittext = view.findViewById(R.id.editTextUsername);
        EditText passwordedittext = view.findViewById(R.id.editTextPassword);
        Button logbutton = view.findViewById(R.id.buttonLogin);
        Button regbutton = view.findViewById(R.id.buttonRegister);
        Button managerbtn = view.findViewById(R.id.buttonManagerRedirect);

        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = useredittext.getText().toString();
                String password = passwordedittext.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Please enter both username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginFunc(v, username, password);
            }
        });

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_first_to_register);
            }
        });
        managerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_first_to_manager_login);
            }
        });

        return view;
    }

    public void loginFunc(View view, String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Navigation.findNavController(view).navigate(R.id.action_first_to_second);
                        } else {
                            Navigation.findNavController(view).navigate(R.id.action_first_to_manager_login);
                            Toast.makeText(getContext(), "Login failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserData(String name, String email, String phoneNumber) {
        FirebaseUtils firebaseUtils = new FirebaseUtils();
        firebaseUtils.saveUserData(name, email);
    }
}