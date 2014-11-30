package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.models.ConcertModel;

import org.joda.time.DateTime;
import org.w3c.dom.Text;

/**
 * Created by katecatlin on 11/29/14.
 */
public class ConcertDetailFragment extends Fragment {

    private static final String ARG_CONCERT_ENTRY = "arg_concert_entry";
    private TextView detailArtistTextView, detailDateTextView, detailTimeTextView, detailVenueTextView,
            detailAddressTextView, detailCityTextView, detailZipCodeTextView, detailUrlTextView,
            featuringTextView, detailArtist2TextView, detailArtist3TextView;

    public static ConcertDetailFragment newInstance(ConcertModel concertModel) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_CONCERT_ENTRY, concertModel);

        ConcertDetailFragment concertDetailFragment = new ConcertDetailFragment();
        concertDetailFragment.setArguments(args);

        return concertDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_view, container, false);

        detailArtistTextView = (TextView) view.findViewById(R.id.detailArtistTextView);
        detailDateTextView = (TextView) view.findViewById(R.id.detailDateTextView);
        detailTimeTextView = (TextView) view.findViewById(R.id.detailTimeTextView);
        detailVenueTextView = (TextView) view.findViewById(R.id.detailVenueTextView);
        detailAddressTextView = (TextView) view.findViewById(R.id.detailAddressTextView);
        detailCityTextView = (TextView) view.findViewById(R.id.detailCityTextView);
        detailZipCodeTextView = (TextView) view.findViewById(R.id.detailZipCodeTextView);
        detailUrlTextView = (TextView) view.findViewById(R.id.detailUrlTextView);
        featuringTextView = (TextView) view.findViewById(R.id.featuringTextView);
        detailArtist2TextView = (TextView) view.findViewById(R.id.detailArtist2TextView);
        detailArtist3TextView = (TextView) view.findViewById(R.id.detailArtist3TextView);

        return view;
    }

    //***********Ask Matt why these are separated in mentor app :)******************

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConcertModel concertModel = getArguments().getParcelable(ARG_CONCERT_ENTRY);

        if (concertModel != null) {

            detailArtistTextView.setText(concertModel.getArtist1());
            detailDateTextView.setText(concertModel.convertDateTimetoDate(concertModel.getDateTime()));
            detailTimeTextView.setText(concertModel.convertDateTimetoTime(concertModel.getDateTime()));
            detailVenueTextView.setText(concertModel.getVenue());
            detailAddressTextView.setText(concertModel.getAddress());
            detailCityTextView.setText(concertModel.getCity());
            detailZipCodeTextView.setText(concertModel.getZipCode());
            detailUrlTextView.setText(concertModel.getTicketUrl());
            detailArtist2TextView.setText(concertModel.getArtist2());
            detailArtist3TextView.setText(concertModel.getArtist3());
            if (concertModel.getArtist2().equals("")) {
                featuringTextView.setText("");
            }

        } else {
            throw new IllegalStateException("Something went wrong passing concert model to detail fragment!");
        }
    }

}
