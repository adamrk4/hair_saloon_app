package com.example.final_p.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_p.R; // Replace with your package name
import com.example.final_p.classes.Appointment;

import java.util.ArrayList;
import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    public List<Appointment> appointmentList;
    private AppointmentClickListener listener; // Interface for click events

    public Adapter1(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Adapter1() {
        this.appointmentList = new ArrayList<>();
    }

    // Setter for appointment list
    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
        notifyDataSetChanged();
    }

    // Setter for appointment click listener
    public void setListener(AppointmentClickListener listener) {
        this.listener = listener;
    }
    // Method to update appointment status
    public void updateAppointmentStatus(int position, boolean isAvailable) {
        if (position >= 0 && position < appointmentList.size()) {
            Appointment appointment = appointmentList.get(position);
            appointment.setAvailable(isAvailable); // Update appointment status
            notifyItemChanged(position); // Notify adapter of data change at specific position
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apt_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        // Set the appointment time to the TextView
        holder.aptTime.setText(appointment.getTime());

        // Set click listener for the order button
        holder.orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onAppointmentClicked(appointment, adapterPosition); // Pass adapter position to listener
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentList != null ? appointmentList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText aptTime;
        Button orderBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aptTime = itemView.findViewById(R.id.apt_time);
            orderBtn = itemView.findViewById(R.id.order_btn);
        }
    }

    // Interface for click events
    public interface AppointmentClickListener {
        void onAppointmentClicked(Appointment appointment, int position);
    }
    public void removeAppointment(int position) {
        if (position >= 0 && position < appointmentList.size()) {
            appointmentList.remove(position);
            notifyItemRemoved(position);
        }
    }

}
