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
import com.example.katecatlin.finalproject.interfaces.FragmentControllerNewConcert;
import com.example.katecatlin.finalproject.models.Concert;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWho extends Fragment {

    private Concert submittedConcert;
    private EditText edit_artist_1, edit_artist_2, edit_artist_3;
    private ImageButton next_button;
    static final String SUBMITTED_CONCERT_ENTRY = "SUBMITTED_CONCERT_ENTRY";


    public static SubmitConcertWho newInstance(){

        Bundle args = new Bundle();
        SubmitConcertWho submitConcertWho = new SubmitConcertWho();
        submitConcertWho.setArguments(args);
        return submitConcertWho;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        submittedConcert = AddConcertActivity.newConcert;
        Log.d("LOG_TAG", submittedConcert.getArtist1() + submittedConcert.getCity() + submittedConcert.getAddress() + submittedConcert.getDateTime().toString());


        View view = inflater.inflate(R.layout.fragment_submit_concert_who, container, false);
        edit_artist_1 = (EditText) view.findViewById(R.id.edit_artist_1);
        edit_artist_2 = (EditText) view.findViewById(R.id.edit_artist_2);
        edit_artist_3 = (EditText) view.findViewById(R.id.edit_artist_3);
        next_button = (ImageButton) view.findViewById(R.id.button_next);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_artist_1.getText().length() != 0) {

                    String artist1 = edit_artist_1.getText().toString();
                    String artist2 = edit_artist_2.getText().toString();
                    String artist3 = edit_artist_3.getText().toString();
                    submittedConcert.setArtist1(artist1);
                    submittedConcert.setArtist2(artist2);
                    submittedConcert.setArtist3(artist3);

                    SubmitConcertWhere submitConcertWhere = SubmitConcertWhere.newInstance();

                    FragmentControllerNewConcert fragmentController = (FragmentControllerNewConcert) getActivity();
                    fragmentController.changeFragment(submitConcertWhere, true, submittedConcert);

                } else {
                    Toast toast = Toast.makeText(getActivity(), "Enter in a headlining artist to proceed!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


}
