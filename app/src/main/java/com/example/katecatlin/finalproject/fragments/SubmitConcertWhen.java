package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.katecatlin.finalproject.R;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWhen extends Fragment {

    public static final SubmitConcertWhen newInstance(String message)
    {
        SubmitConcertWhen fragment = new SubmitConcertWhen();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_concert_when, container, false);

        return view;
    }
}
