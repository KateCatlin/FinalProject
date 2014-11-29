package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.example.katecatlin.finalproject.models.ConcertModel;

/**
 * Created by katecatlin on 11/29/14.
 */
public class ConcertDetailFragment extends Fragment {

    private static final String ARG_CONCERT_ENTRY = "arg_concert_entry";

    public static ConcertDetailFragment newInstance(ConcertModel concertModel) {

        Bundle args = new Bundle();
//        args.putParcelable(ARG_CONCERT_ENTRY, concertModel);

        ConcertDetailFragment concertDetailFragment = new ConcertDetailFragment();
        concertDetailFragment.setArguments(args);

        return concertDetailFragment;
    }


}
