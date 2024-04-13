package com.example.final_p.classes;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String phoneNumber;
    private String email;
    private List<Appointment> appointments; // List to store client's appointments

    public Client(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appointments = new ArrayList<>(); // Initialize the list of appointments
    }

    public Client() {
        this.appointments = new ArrayList<>(); // Initialize the list of appointments
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for appointments
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Method to add an appointment to the client's list of appointments
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}
