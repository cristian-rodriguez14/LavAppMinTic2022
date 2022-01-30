package com.example.lavapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lavapp.R;

public class ListPreciosFragment extends Fragment {

    public ListPreciosFragment() {
        // Required empty public constructor
    }

    public static ListPreciosFragment newInstance(String param1, String param2) {
        ListPreciosFragment fragment = new ListPreciosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_precios, container, false);
    }
}