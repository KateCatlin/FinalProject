package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.models.ConcertModel;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWhere extends Fragment {

    private static final String SUBMITTED_CONCERT_ENTRY = "SUBMITTED_CONCERT_ENTRY";

    public static final SubmitConcertWhere newInstance(ConcertModel concertModel) {
        Bundle args = new Bundle();
        args.putParcelable(SUBMITTED_CONCERT_ENTRY, concertModel);

        SubmitConcertWhere submitConcertWhere = new SubmitConcertWhere();
        submitConcertWhere.setArguments(args);

        return submitConcertWhere;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_concert_where, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ConcertModel submittedConcert = getArguments().getParcelable(SUBMITTED_CONCERT_ENTRY);
        Log.d("LOG_TAG", "Submitted concert info on artist is " + submittedConcert.getArtist1());

    }


}

