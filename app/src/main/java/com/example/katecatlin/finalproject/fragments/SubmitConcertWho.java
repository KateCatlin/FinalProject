package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.models.ConcertModel;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWho extends Fragment {

    private EditText edit_artist_1, edit_artist_2, edit_artist_3;
    public static final String PASS_TO_SCREEN_2 = "PASS_TO_SCREEN_2";


    public static SubmitConcertWho newInstance(){
        SubmitConcertWho submitConcertWho =  new SubmitConcertWho();
        return submitConcertWho;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_concert_who, container, false);

        final ConcertModel submittedConcert = new ConcertModel(null, "", "", "", "", "", "", "", "", "");


        ImageButton next_button;

        edit_artist_1 = (EditText) view.findViewById(R.id.edit_artist_1);
        edit_artist_2 = (EditText) view.findViewById(R.id.edit_artist_2);
        edit_artist_3 = (EditText) view.findViewById(R.id.edit_artist_3);

        next_button = (ImageButton) view.findViewById(R.id.button_next);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String artist1 = edit_artist_1.getText().toString();
                String artist2 = edit_artist_2.getText().toString();
                String artist3 = edit_artist_3.getText().toString();

                if (artist1 != null) {

                    submittedConcert.setArtist1(artist1);
                    submittedConcert.setArtist2(artist2);
                    submittedConcert.setArtist3(artist3);

                    SubmitConcertWhere submitConcertWhere = SubmitConcertWhere.newInstance(submittedConcert);

                    FragmentController fragmentController = (FragmentController) getActivity();
                    fragmentController.changeFragment(submitConcertWhere, true);


                }

            }
        });

        return view;
    }


}
