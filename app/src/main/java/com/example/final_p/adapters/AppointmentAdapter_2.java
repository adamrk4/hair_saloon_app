package com.example.final_p.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_p.R;
import com.example.final_p.classes.Appointment;
import com.example.final_p.classes.Client;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppointmentAdapter_2 extends RecyclerView.Adapter<AppointmentAdapter_2.ViewHolder> {
    private List<Appointment> appointmentsList;

    public AppointmentAdapter_2(List<Appointment> appointmentsList) {
        this.appointmentsList = appointmentsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apt_description, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointmentsList.get(position);
        holder.bind(appointment);
    }

    @Override
    public int getItemCount() {
        return appointmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView descriptionTextView;
        private TextView dateText;
        private TextView clientNameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.time_txt);
            dateText = itemView.findViewById(R.id.date_txt);
            descriptionTextView = itemView.findViewById(R.id.desc_txt);

        }

        public void bind(Appointment appointment) {
            timeTextView.setText(appointment.getTime());
            dateText.setText(String.valueOf(appointment.getDate()));
            descriptionTextView.setText(appointment.getDescription());
            clientNameTextView.setText(appointment.getClient().getName());
        }
    }
}
