package com.example.final_p.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_p.R;
import com.example.final_p.classes.Appointment;
import java.util.List;

import com.example.final_p.classes.Client;

public class ClientAppointmentsAdapter extends RecyclerView.Adapter<ClientAppointmentsAdapter.ViewHolder> {
    private List<Client> clients;
    private List<Appointment> appointments;

    public ClientAppointmentsAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apt_description, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.bind(client);
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeTxt, dateTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTxt = itemView.findViewById(R.id.time_txt);
            dateTxt = itemView.findViewById(R.id.date_txt);
        }

        public void bind(Client client) {
            // Assuming getClientAppointments() returns a list of appointments for the client
            List<Appointment> appointments = client.getAppointments();
            // Displaying only the first appointment for demonstration purposes
            if (!appointments.isEmpty()) {
                Appointment appointment = appointments.get(0);
                timeTxt.setText(appointment.getTime());
                dateTxt.setText(appointment.getDate());
            }
        }
    }
}
