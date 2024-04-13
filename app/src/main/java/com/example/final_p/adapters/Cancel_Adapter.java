package com.example.final_p.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_p.R;

import java.util.List;

public class Cancel_Adapter extends RecyclerView.Adapter<Cancel_Adapter.ViewHolder> {
    private List<String> appointments; // Assuming list of String for demonstration

    public Cancel_Adapter(List<String> appointments) {
        this.appointments = appointments;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cancel_card, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position
        // Replace the contents of the view with that element
        holder.textView.setText(appointments.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return appointments.size();
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define the view that holds the content
            textView = view.findViewById(R.id.time_txt); // Change to your TextView id
        }
    }
}
