package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.activities.AddConcertActivity;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.interfaces.FragmentControllerNewConcert;
import com.example.katecatlin.finalproject.models.ConcertModel;

/**
 * Created by katecatlin on 12/3/14.
 */


public class SubmitConcertWhere extends Fragment {

    private EditText edit_venue, edit_address, edit_city, edit_zipcode, edit_venue_url;
    private ImageButton next_button;
    private ConcertModel submittedConcert;

    public static final SubmitConcertWhere newInstance() {

        Bundle args = new Bundle();
        SubmitConcertWhere submitConcertWhere = new SubmitConcertWhere();
        submitConcertWhere.setArguments(args);

        return submitConcertWhere;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_concert_where, container, false);

        submittedConcert = AddConcertActivity.newConcert;

        edit_venue = (EditText) view.findViewById(R.id.edit_venue);
        edit_address = (EditText) view.findViewById(R.id.edit_address);
        edit_city = (EditText) view.findViewById(R.id.edit_city);
        edit_zipcode = (EditText) view.findViewById(R.id.edit_zipcode);
        edit_venue_url = (EditText) view.findViewById(R.id.edit_venue_url);
        next_button = (ImageButton) view.findViewById(R.id.button_next);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_venue.getText().length() != 0 ) {
                    gatherInfoFromWhereEditTexts();
                    SubmitConcertWhen submitConcertWhen = SubmitConcertWhen.newInstance();

                    FragmentControllerNewConcert fragmentController = (FragmentControllerNewConcert) getActivity();
                    fragmentController.changeFragment(submitConcertWhen, true, submittedConcert);

                } else {
                    Toast toast = Toast.makeText(getActivity(), "You must at least enter venue name!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


    public void gatherInfoFromWhereEditTexts () {
        String venue = edit_venue.getText().toString();
        submittedConcert.setVenue(venue);
        String address = edit_address.getText().toString();
        submittedConcert.setAddress(address);
        String city = edit_city.getText().toString();
        submittedConcert.setCity(city);
        String zipCode = edit_zipcode.getText().toString();
        submittedConcert.setZipCode(zipCode);
        String venueURL = edit_venue_url.getText().toString();
        submittedConcert.setVenueURL(venueURL);
    }
}


