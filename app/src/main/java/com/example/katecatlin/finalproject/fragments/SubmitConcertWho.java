package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katecatlin.finalproject.R;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWho extends android.support.v4.app.Fragment {

    public static final SubmitConcertWho newInstance(String message)
    {
        SubmitConcertWho fragment = new SubmitConcertWho();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_concert_who, container, false);

        return view;
    }
}
