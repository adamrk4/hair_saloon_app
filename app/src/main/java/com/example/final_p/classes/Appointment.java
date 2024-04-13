package com.example.final_p.classes;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Appointment {
    private String time; // Time of the appointment
    private String description; // Description of the appointment
    private boolean isAvailable; // Availability of the appointment
    private int date;
    private boolean isMyAppointment;
    private int year;
    private int month;
    private int day;
    private Client client; // Reference to the client associated with this appointment

    public Appointment(String time, String description, boolean isAvailable, int year, int month, int day, Client client) {
        this.time = time;
        this.description = description;
        this.isAvailable = isAvailable;
        this.year = year;
        this.month = month;
        this.day = day;
        this.client = client; // Set the client for this appointment
        this.isMyAppointment = false;
    }
    // Getters and setters
    public boolean isMyAppointment() {
        return isMyAppointment;
    }

    public void setMyAppointment(boolean myAppointment) {
        isMyAppointment = myAppointment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    // Getters and setters for client
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    // Getter and setter for client name
    public String getClientName() {
        if (client != null) {
            return client.getName();
        }
        return "";
    }

    // Getter for appointment time
    public String getAppointmentTime() {
        return time;
    }
    // Static method to fetch appointments from Firebase database
    public static void getAppointmentsFromFirebase(DatabaseReference databaseReference, ValueEventListener valueEventListener) {
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }
}

