package com.example.final_p.fragments;

import static com.example.final_p.classes.Appointment.getAppointmentsFromFirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_p.R;
import com.example.final_p.adapters.Adapter1;
import com.example.final_p.classes.Appointment;
import com.example.final_p.classes.Client;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class making_appointment extends Fragment implements Adapter1.AppointmentClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerViewClientAppointments;
    private TextView textViewSelectedDate;
    private CalendarView calendarView;
    private Client client;
    private Adapter1 adapter;
    private List<Appointment> appointments; // Corrected: Moved to global scope
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private DatabaseReference appointmentsRef;

    public making_appointment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appointmentsRef = FirebaseDatabase.getInstance().getReference().child("appointments");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.making_appointment, container, false);

        textViewSelectedDate = view.findViewById(R.id.textViewSelectedDate);
        calendarView = view.findViewById(R.id.calendarView);
        recyclerViewClientAppointments = view.findViewById(R.id.recyclerViewAppointments);

        // Initialize RecyclerView
        recyclerViewClientAppointments.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter1(new ArrayList<>());
        recyclerViewClientAppointments.setAdapter(adapter);

        // Initialize Firebase Database reference
        appointmentsRef = FirebaseDatabase.getInstance().getReference().child("appointments");

        // Set initial date when the fragment is created
        Calendar calendar = Calendar.getInstance();
        textViewSelectedDate.setText(formatDate(calendar.getTime()));

        // Update selected date when the user selects a date in the CalendarView
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            // Update the TextView with the selected date
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            textViewSelectedDate.setText(formatDate(selectedDate.getTime()));

            // Retrieve appointments for the selected date
            retrieveAppointmentsForDate(year, month, dayOfMonth);
        });

        return view;
    }
    private void retrieveAppointmentsForDate(int year, int month, int dayOfMonth) {
        // Retrieve appointments for the selected date from Firebase
        appointmentsRef.child(year + "-" + month + "-" + dayOfMonth).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }

                // Update RecyclerView with the new data
                updateRecyclerView(appointments);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.e("Firebase", "Failed to retrieve appointments", databaseError.toException());
            }
        });
    }

    // Inside your One fragment
    private void updateRecyclerView(List<Appointment> appointments) {
        // Initialize a new instance of the adapter and set appointments
        if (adapter == null) {
            adapter = new Adapter1(); // Corrected typo here
            recyclerViewClientAppointments.setAdapter(adapter);
        }
        adapter.setAppointmentList(appointments); // Change this line to setAppointmentList
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView

        client = new Client();

        recyclerViewClientAppointments.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter1(appointments);
        adapter.setListener(this); // Pass the fragment reference
        recyclerViewClientAppointments.setAdapter(adapter);
    }


    // Method to update appointment status to unavailable
    private void updateAppointmentStatus(int position) {
        if (position >= 0 && position < appointments.size()) {
            Appointment clickedAppointment = appointments.get(position);
            clickedAppointment.setAvailable(false); // Mark appointment as unavailable

            // Refresh RecyclerView to reflect the change
            adapter.notifyItemChanged(position);
        }
    }
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(date);
    }
    private String incrementHour(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int newHour = (hour + 1) % 24; // Wrap around to next day if hour exceeds 23
        return String.format("%02d", newHour) + ":" + parts[1]; // Format hour to two digits
    }


    @Override
    public void onAppointmentClicked(Appointment appointment, int position) {
        // Check if adapter is initialized
        if (adapter != null) {
            // Remove the item from the adapter's dataset
            client.addAppointment(appointment); // Assuming this is necessary
            adapter.removeAppointment(position); // Assuming this method removes the appointment correctly
            // Notify the adapter that an item has been removed
            //adapter.notifyItemRemoved(position); // You may not need this if removeAppointment handles notification
            appointments.remove(appointment); // Assuming appointments is synchronized with adapter.appointmentList
        }
    }


}