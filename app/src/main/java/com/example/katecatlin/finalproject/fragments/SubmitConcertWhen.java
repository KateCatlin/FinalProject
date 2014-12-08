package com.example.katecatlin.finalproject.fragments;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.dialogs.DatePickerFragment;
import com.example.katecatlin.finalproject.dialogs.TimePickerFragment;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.requests.ParseRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWhen extends Fragment {
    static final String SUBMITTED_CONCERT_ENTRY = SubmitConcertWho.SUBMITTED_CONCERT_ENTRY;
    ConcertModel submittedConcert;
    private EditText edit_ticket_url;
    Button button_date, button_time;
    Calendar dateAndTime = Calendar.getInstance();
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    DateTime dt = new DateTime("2004-12-13T21:39:45.618-08:00");



    public static final SubmitConcertWhen newInstance(ConcertModel concertModel) {
        Bundle args = new Bundle();
        args.putParcelable(SUBMITTED_CONCERT_ENTRY, concertModel);

        SubmitConcertWhen submitConcertWhen = new SubmitConcertWhen();
        submitConcertWhen.setArguments(args);

        return submitConcertWhen;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_concert_when, container, false);

        submittedConcert = getArguments().getParcelable(SUBMITTED_CONCERT_ENTRY);

        edit_ticket_url = (EditText) view.findViewById(R.id.edit_ticket_url);
        ImageButton button_back = (ImageButton) view.findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_ticket_url != null) {
                    gatherInfoFromWhenEditTexts();
                }

                SubmitConcertWho submitConcertWho = SubmitConcertWho.newInstance(submittedConcert);

                FragmentController fragmentController = (FragmentController) getActivity();
                fragmentController.changeFragment(submitConcertWho, true);

            }
        });
        Button button_submit = (Button) view.findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gatherInfoFromWhenEditTexts();

                ParseRequest parseRequest = ParseRequest.getEstablishedParseRequest(getActivity());
                parseRequest.postConcertToParse(submittedConcert);
            }
        });

        Button button_date = (Button) view.findViewById(R.id.button_date);
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        Button button_time = (Button) view.findViewById(R.id.button_time);
        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        return view;
    }

    public void gatherInfoFromWhenEditTexts() {
        String url = edit_ticket_url.getText().toString();
        submittedConcert.setTicketUrl(url);
    }

    public void setButtonText (String value) {
        button_date.setText(value);
    }

    private void updateLabel() {
        button_date
                .setText(DateUtils.formatDateTime(getActivity(),
                        dateAndTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME));
    }
}

