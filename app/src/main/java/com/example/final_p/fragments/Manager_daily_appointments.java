package com.example.final_p.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_p.R;
import com.example.final_p.adapters.ManagerAdapter;
import com.example.final_p.classes.Appointment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Manager_daily_appointments extends Fragment {

    private RecyclerView recyclerViewAppointments;
    private ManagerAdapter adapter;
    private DatabaseReference appointmentsRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager_daily_appointments, container, false);

        recyclerViewAppointments = view.findViewById(R.id.recyclerViewAppointments);

        // Initialize Firebase Database reference
        appointmentsRef = FirebaseDatabase.getInstance().getReference().child("appointments");

        // Set up RecyclerView and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAppointments.setLayoutManager(layoutManager);
        adapter = new ManagerAdapter(new ArrayList<>()); // Initialize adapter with an empty list
        recyclerViewAppointments.setAdapter(adapter);

        // Retrieve appointments from Firebase
        retrieveAppointmentsFromFirebase();

        return view;
    }

    private void retrieveAppointmentsFromFirebase() {
        // Attach a ValueEventListener to fetch appointments from Firebase
        appointmentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Appointment> appointments = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Convert each snapshot to an Appointment object
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                // Update the adapter with the retrieved appointments
                adapter.setAppointments(appointments);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to retrieve appointments", databaseError.toException());
            }
        });
    }
}

