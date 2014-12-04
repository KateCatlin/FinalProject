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

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWhen extends Fragment
//        implements GetChosenDateInterface
{
    static final String SUBMITTED_CONCERT_ENTRY = SubmitConcertWho.SUBMITTED_CONCERT_ENTRY;
    ConcertModel submittedConcert;
    private EditText edit_ticket_url;
    Button button_date, button_time;
    public static final String WHICH_DATE_KEY = "WHICH_DATE_KEY";
    Calendar dateAndTime = Calendar.getInstance();


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
//                Insert some stuff about sending to Parse

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });

        Button button_date = (Button) view.findViewById(R.id.button_date);

        return view;
    }

    public void gatherInfoFromWhenEditTexts() {
        String url = edit_ticket_url.getText().toString();
        submittedConcert.setTicketUrl(url);
    }

    private void updateLabel() {
        button_date
                .setText(DateUtils.formatDateTime(getActivity(),
                        dateAndTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME));
    }
}


//    @Override
//    public void getChosenDate(Date chosenDate, String whichDateString) {
//
//        if(whichDateString.equals(END_DATE_STRING)){
//            endDate = chosenDate;
//            lunchEndButton.setText(fmt.print(endDate));
//        }
//
//    }

