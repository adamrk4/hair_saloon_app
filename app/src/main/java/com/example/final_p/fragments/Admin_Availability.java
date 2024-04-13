package com.example.final_p.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.final_p.R;
import com.example.final_p.classes.Appointment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Admin_Availability extends Fragment {

    private Button toggleButtonSunday;
    private CalendarView calendarView;
    private DatabaseReference appointmentsRef;
    private Calendar calendar; // Declare calendar object here



    // Add other UI elements as needed
    private AvailabilityChangeListener availabilityChangeListener;
    public Admin_Availability() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Database reference
        appointmentsRef = FirebaseDatabase.getInstance().getReference().child("appointments");
        // Initialize the calendar object
        calendar = Calendar.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin__availability, container, false);

        // Initialize UI elements
        toggleButtonSunday = view.findViewById(R.id.buttonApplyChanges);
        calendarView = view.findViewById(R.id.calendarViewManager);

        //sunday
        EditText sundayStart= view.findViewById(R.id.editTextSundayStart);
        EditText sundayEnd=view.findViewById(R.id.editTextSundayEnd);

        // Set onClickListener for toggleButtonSunday
        toggleButtonSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateAppointmentDay(sundayStart,sundayEnd);
            }
        });
        return view;
    }
    private void generateAppointmentDay(EditText startTime, EditText endTime) {
        // Get the selected date from the CalendarView
        long selectedDateInMillis = calendarView.getDate();
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(selectedDateInMillis);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is 0-based
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Generate appointments based on the selected date
        List<Appointment> appointments = generateAppointmentsForDay(year, month, day, startTime, endTime);

        // Save appointments to Firebase
        for (Appointment appointment : appointments) {
            appointmentsRef.push().setValue(appointment);
        }
        // Show a toast message indicating successful generation of appointments
        String message = "Appointments generated successfully for " + year + "-" + month + "-" + day;
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();    }

    private List<Appointment> generateAppointmentsForDay(int year, int month, int day, EditText startTime, EditText endTime) {
        // Implement your logic to generate appointments for the specified date
        List<Appointment> appointments = new ArrayList<>();

        // Get start and end times from EditText fields
        String start = startTime.getText().toString();
        String end = endTime.getText().toString();

        // Sample implementation: Generate appointments starting from the start time to the end time
        String currentTime = start;
        while (!currentTime.equals(end)) {
            appointments.add(new Appointment(currentTime, "Description", true, year, month, day, null));
            currentTime = incrementHour(currentTime);
        }

        return appointments;
    }


    // Method to check if a time string is in valid format (HH:mm)
    private boolean isValidTime(String time) {
        return time.matches("^([01]\\d|2[0-3]):([0-5]\\d)$");
    }

    // Method to increment time by an hour
    private String incrementHour(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        // Increment hour by 1
        hour += 1;
        if (hour >= 24) {
            hour = 0; // Reset to 0 if it exceeds 23
        }

        // Convert hour and minute back to string with leading zeros if necessary
        return String.format("%02d:%02d", hour, minute);
    }

    // Method to set all days of the week to be available for appointments
    private void setWeekAvailable() {
        boolean[] availability = new boolean[7]; // Initialize the availability array
        // Set all days to available initially
        for (int i = 0; i < availability.length; i++) {
            availability[i] = true;
        }
        // Notify the listener about the availability change
        if (availabilityChangeListener != null) {
            availabilityChangeListener.onAvailabilityChanged(availability);
        }
    }

    // Setter method for the AvailabilityChangeListener
    // Interface for communicating availability changes to the parent activity
    // Method to set availability for the week
    private void setWeekAvailable(boolean[] availability) {
        if (availabilityChangeListener != null) {
            availabilityChangeListener.onAvailabilityChanged(availability);
        }
    }
    // Setter method for the AvailabilityChangeListener
    public void setAvailabilityChangeListener(AvailabilityChangeListener listener) {
        this.availabilityChangeListener = listener;
    }
    // Interface for communicating availability changes to the parent activity or fragment
    public interface AvailabilityChangeListener {
        void onAvailabilityChanged(boolean[] availability);
    }
}
