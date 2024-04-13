package com.example.final_p.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_p.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class register extends Fragment {
    private FirebaseAuth mAuth;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth here

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.register, container, false);

        EditText usernameEditText = view.findViewById(R.id.email_input);
        EditText passwordEditText = view.findViewById(R.id.password_input);
        EditText phoneNumberEditText = view.findViewById(R.id.phone_input);
        Button reg_btn = view.findViewById(R.id.register_button);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                signUpUser(username, password, phoneNumber);
            }
        });

        return view;
    }

    private void signUpUser(String username, String password, String phoneNumber) {
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Bundle bundle = new Bundle();
                        bundle.putString("userEmail", user.getEmail());
                        Toast.makeText(getContext(), "Sign up successful.", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(view).navigate(R.id.action_register_to_second, bundle);
                    } else {
                        Toast.makeText(getContext(), "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
