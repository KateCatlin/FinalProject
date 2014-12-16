package com.example.katecatlin.finalproject.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.activities.AddConcertActivity;
import com.example.katecatlin.finalproject.activities.MainActivity;
import com.example.katecatlin.finalproject.dialogs.DatePickerFragment;
import com.example.katecatlin.finalproject.dialogs.TimePickerFragment;
import com.example.katecatlin.finalproject.interfaces.GetChosenDateInterface;
import com.example.katecatlin.finalproject.interfaces.GetChosenTimeInterface;
import com.example.katecatlin.finalproject.models.Concert;
import com.example.katecatlin.finalproject.requests.ParseRequest;

import org.joda.time.DateTime;

/**
 * Created by katecatlin on 12/3/14.
 */


public class SubmitConcertWhen extends Fragment implements GetChosenDateInterface, GetChosenTimeInterface {

    static final String SUBMITTED_CONCERT_ENTRY = SubmitConcertWho.SUBMITTED_CONCERT_ENTRY;
    private Concert submittedConcert;
    private EditText edit_ticket_url;
    private Button button_date, button_time, button_submit;
    static int year, month, day, hour, minute = 0;


    public static final SubmitConcertWhen newInstance() {
        Bundle args = new Bundle();
        SubmitConcertWhen submitConcertWhen = new SubmitConcertWhen();
        submitConcertWhen.setArguments(args);

        return submitConcertWhen;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_concert_when, container, false);

        submittedConcert = AddConcertActivity.newConcert;
        Log.d("LOG_TAG", submittedConcert.getArtist1() + submittedConcert.getCity() + submittedConcert.getAddress() + submittedConcert.getDateTime().toString());

        edit_ticket_url = (EditText) view.findViewById(R.id.edit_ticket_url);
        button_submit = (Button) view.findViewById(R.id.button_submit);
        button_date = (Button) view.findViewById(R.id.button_date);
        button_time = (Button) view.findViewById(R.id.button_time);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (year != 0 && hour != 0) {
                    gatherInfoFromWhenEditTexts();
                    ParseRequest parseRequest = ParseRequest.getEstablishedParseRequest(getActivity());
                    parseRequest.postConcertToParse(submittedConcert);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            else {
                    Toast toast = Toast.makeText(getActivity(), "You must enter a date and time!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment(SubmitConcertWhen.this);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment(SubmitConcertWhen.this);
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
    }


        public void gatherInfoFromWhenEditTexts() {
        String url = edit_ticket_url.getText().toString();
        if (url.length()!=0 && !url.startsWith("http://")) {
            url = "http://" + url;
        }
        submittedConcert.setTicketUrl(url);

        DateTime dt = new DateTime(year+"-"+month+"-"+day+"T"+hour+":"+minute+":00.000");
        submittedConcert.setDateTime(dt);
    }


    @Override
    public void getChosenDate(int year, int month, int day) {
        this.year = year;
        this.month = month + 1;
        this.day = day;
        button_date.setText(month + "/" + day + "/" + year);
    }


    @Override
    public void getChosenTime(int hour, int minute) {
        if (hour > 12) {
            if (minute < 10 ) {
                button_time.setText((hour-12) + ":0" + minute);
            } else {
                button_time.setText((hour-12) + ":" + minute);
            }
        } else {
            button_time.setText(hour + ":" + minute);
        }

        this.hour = hour;
        this.minute = minute;
    }
}

