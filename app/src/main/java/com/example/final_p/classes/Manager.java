package com.example.final_p.classes;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Manager {
    private String name;
    private String phoneNumber;
    private String email;
    private Set<String> appointmentDates;
    private Map<String, Set<Appointment>> appointmentsByDate;

    public Manager(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public Manager() {
        this.appointmentsByDate = new HashMap<>();
    }

    // Method to add appointment for a specific date
    public void addAppointment(String date, Appointment appointment) {
        if (!appointmentsByDate.containsKey(date)) {
            appointmentsByDate.put(date, new HashSet<>());
        }
        appointmentsByDate.get(date).add(appointment);
    }

    // Method to remove appointment for a specific date
    public void removeAppointment(String date, Appointment appointment) {
        if (appointmentsByDate.containsKey(date)) {
            appointmentsByDate.get(date).remove(appointment);
        }
    }

    // Method to check if a date has appointments
    public boolean hasAppointments(String date) {
        return appointmentsByDate.containsKey(date) && !appointmentsByDate.get(date).isEmpty();
    }

    // Method to get appointments for a specific date
    public Set<Appointment> getAppointments(String date) {
        return appointmentsByDate.getOrDefault(date, new HashSet<>());
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

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
