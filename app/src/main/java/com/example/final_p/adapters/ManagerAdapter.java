package com.example.final_p.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_p.R;
import com.example.final_p.classes.Appointment;


import android.widget.TextView;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder> {
    private List<Appointment> appointments;

    public ManagerAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apt_description, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);

        // Set client name
        holder.textViewClientName.setText("שם לקוח: " + appointment.getClientName());

        // Set appointment time
        holder.textViewAppointmentTime.setText("שעה: " + appointment.getAppointmentTime());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        notifyDataSetChanged();

    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewClientName;
        TextView textViewAppointmentTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewClientName = itemView.findViewById(R.id.textView);
            textViewAppointmentTime = itemView.findViewById(R.id.textView3);
        }
    }
}
