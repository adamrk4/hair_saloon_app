package com.example.final_p.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.final_p.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link second#newInstance} factory method to
 * create an instance of this fragment.
 */
public class second extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public second() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment second.
     */
    // TODO: Rename and change types and number of parameters
    public static second newInstance(String param1, String param2) {
        second fragment = new second();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second, container, false);

        // Find the buttons
        Button buttonNewAppointment = view.findViewById(R.id.buttonNewAppointment);
        Button buttonCancelAppointment = view.findViewById(R.id.buttonCancelAppointment);
        Button buttonViewAppointments = view.findViewById(R.id.buttonViewAppointments);
        Button buttonEditProfile = view.findViewById(R.id.buttonEditProfile);
        Button buttonContactUs = view.findViewById(R.id.buttonContactUs);

        // Set OnClickListener for each button
        buttonNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the specified destination
                NavHostFragment.findNavController(second.this)
                        .navigate(R.id.making_appointment);
            }
        });

        buttonCancelAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the specified destination
                NavHostFragment.findNavController(second.this)
                        .navigate(R.id.action_second_to_cancelAPT);
            }
        });
        buttonViewAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the specified destination
                NavHostFragment.findNavController(second.this)
                        .navigate(R.id.action_second_to_my_appointments);
            }
        });


        // Similarly, set OnClickListener for other buttons

        return view;
    }

}
