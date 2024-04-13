package com.example.final_p.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.final_p.R;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_p.adapters.Adapter1;
import com.example.final_p.adapters.ClientAppointmentsAdapter;
import com.example.final_p.classes.Appointment;
import com.example.final_p.classes.Client;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class My_appointments extends Fragment  {
    private RecyclerView recyclerViewAppointments;
    private Client client;
    private ClientAppointmentsAdapter adapter2;
    private DatabaseReference clientAppointmentsRef;

    public My_appointments() {
        // Required empty public constructor
    }

    private static final String ARG_CLIENT_ID = "client_id";

    public static My_appointments newInstance(String clientId) {
        My_appointments fragment = new My_appointments();
        Bundle args = new Bundle();
        args.putString(ARG_CLIENT_ID, clientId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientAppointmentsRef = FirebaseDatabase.getInstance().getReference().child("appointments");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_appointments, container, false);

        recyclerViewAppointments = view.findViewById(R.id.recyclerViewAppointments);

        // Initialize adapter with an empty list of appointments initially
        adapter2 = new ClientAppointmentsAdapter(new ArrayList<>());

        // Set adapter to RecyclerView
        recyclerViewAppointments.setAdapter(adapter2);

        // Attach listener to client appointments reference
        attachClientAppointmentsListener();

        return view;
    }

    // Method to attach listener to client appointments reference
    private void attachClientAppointmentsListener() {
        clientAppointmentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear previous appointments
                List<Appointment> appointments = new ArrayList<>();
                // Loop through appointments and add them to the list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                // Update adapter with the new list of appointments
                adapter2.setAppointments(appointments);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e("Firebase", "Error fetching client appointments", databaseError.toException());
            }
        });
    }
}


