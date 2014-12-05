package com.example.katecatlin.finalproject.fragments;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import com.example.katecatlin.finalproject.activities.MainActivity;
import com.example.katecatlin.finalproject.dialogs.DatePickerFragment;
import com.example.katecatlin.finalproject.dialogs.TimePickerFragment;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.interfaces.GetChosenDateInterface;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.requests.ParseRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWhen extends Fragment {
    static final String SUBMITTED_CONCERT_ENTRY = SubmitConcertWho.SUBMITTED_CONCERT_ENTRY;
    ConcertModel submittedConcert;
    private EditText edit_ticket_url;
    Button button_date, button_time;
    public static final String WHICH_DATE_KEY = "WHICH_DATE_KEY";
    Calendar dateAndTime = Calendar.getInstance();
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");



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

//                Log.d("LOG_TAG", "All the info from the concert is " + submittedConcert.getArtist1() + " " + submittedConcert.getVenue() + " " + submittedConcert.getTicketUrl() + " " + submittedConcert.getAddress());
                ParseRequest parseRequest = new ParseRequest(getActivity());
                parseRequest.postConcertToParse(submittedConcert);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });

        Button button_date = (Button) view.findViewById(R.id.button_date);
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate(view);
            }
        });

        Button button_time = (Button) view.findViewById(R.id.button_time);
        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseTime(view);
            }
        });
        return view;
    }

    com.example.katecatlin.finalproject.interfaces.GetChosenDateInterface chosenDateInterface;

        public void chooseDate(View view) { new DatePickerDialog(getActivity(), d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show(); }

        DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                if (chosenDateInterface !=null) {
                    chosenDateInterface.getChosenDate(dateAndTime.getTime());
                }
            }
        };

    public void chooseTime(View v) { new TimePickerDialog(getActivity(), t,
            dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime.get(Calendar.MINUTE), true)
            .show(); }

        TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {

                dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateAndTime.set(Calendar.MINUTE, minute);
                Log.d("LOG_TAG", "dateAndTime is" + dateAndTime);
                if (button_time == null) {
                    Log.d("LOG_TAG", "button_time is null");
                }
                if (dateAndTime != null) {
//                    button_time.setText(DateUtils.formatDateTime(getActivity(),
//                            dateAndTime.getTimeInMillis(),
//                            DateUtils.FORMAT_SHOW_TIME));
                }
                else {
                    Log.d("LOG_TAG", "dateAndTime is null");
                }
            }
        };


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

