package com.example.final_p.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.final_p.R;
import com.example.final_p.classes.Manager;
import com.example.final_p.classes.WorkingHours;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class adminDashboard extends Fragment {

    private EditText managerNameEditText, managerPhoneEditText, managerEmailEditText;
    private RecyclerView holidaysRecyclerView, unavailablePeriodsRecyclerView;
    private Button openavailability,myappointmentsDay;
    private FirebaseDatabase database;
    private DatabaseReference settingsRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_dashboard, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find UI components by their IDs

        openavailability =view.findViewById(R.id.button2);
        myappointmentsDay=view.findViewById(R.id.button);

        // Initialize Firebase Realtime Database
        database = FirebaseDatabase.getInstance();
        settingsRef = database.getReference("settings");
        openavailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_adminDashboard_to_admin_Availability);
            }
        });;
        myappointmentsDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_adminDashboard_to_admin_Availability);
            }
        });;
        // Load existing data and populate UI components
        loadManagerInformation();

    }

    private void loadManagerInformation() {
        // Load manager information from Firebase Realtime Database
        settingsRef.child("manager").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Manager manager = snapshot.getValue(Manager.class);
                if (manager != null) {
                    managerNameEditText.setText(manager.getName());
                    managerPhoneEditText.setText(manager.getPhoneNumber());
                    managerEmailEditText.setText(manager.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }




    private void saveSettings() {
        // Get the updated values from the UI components
        String name = managerNameEditText.getText().toString().trim();
        String phone = managerPhoneEditText.getText().toString().trim();
        String email = managerEmailEditText.getText().toString().trim();


        // Create data objects with the updated values
        Manager updatedManager = new Manager(name, phone, email);

        // Save the updated data to Firebase Realtime Database
        settingsRef.child("manager").setValue(updatedManager);


        // Save any changes to the holidays and unavailable periods
        saveHolidaysAndPeriods();
    }

    private void saveHolidaysAndPeriods() {
        // Save any changes to the holidays and unavailable periods data
        // Implement the logic to update the holidays and unavailable periods in Firebase Realtime Database
    }
}