package com.example.final_p.fragments;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_p.R;
import com.example.final_p.adapters.Cancel_Adapter;
import com.example.final_p.classes.Appointment;
import com.example.final_p.classes.Client;

import java.util.List;

public class CancelAPT extends Fragment {

    private Client client;
    private List<Appointment> appointmentlist;
    private Cancel_Adapter adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public CancelAPT() {
        // Required empty public constructor
    }
    public static CancelAPT newInstance(String param1, String param2) {
        CancelAPT fragment = new CancelAPT();
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
        return inflater.inflate(R.layout.cancel_a_p_t, container, false);
    }
}
