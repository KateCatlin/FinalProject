package com.example.katecatlin.finalproject.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.dialogs.DatePickerFragment;
import com.example.katecatlin.finalproject.dialogs.TimePickerFragment;
import com.example.katecatlin.finalproject.fragments.ConcertListFragment;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWho;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.models.ConcertModel;

import java.util.Calendar;


public class AddConcertActivity extends Activity implements FragmentController {
    Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        ConcertModel newConcert = new ConcertModel(null, "", "", "", "", "", "", "", "", "");

        SubmitConcertWho submitConcertWho = SubmitConcertWho.newInstance(newConcert);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, submitConcertWho)
                .commit();

    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void changeFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void setDateButton (int hourOfDay, int minute) {
        Button button_date = (Button) findViewById(R.id.button_date);
        button_date.setText(hourOfDay + ":" + minute);
    }

    public void chooseDate(View v) { new DatePickerDialog(this, d,
            dateAndTime.get(Calendar.YEAR),
            dateAndTime.get(Calendar.MONTH),
            dateAndTime.get(Calendar.DAY_OF_MONTH))
            .show(); }

    public void chooseTime(View v) { new TimePickerDialog(this, t,
            dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime.get(Calendar.MINUTE), true)
            .show(); }


    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        } };

    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay,
                              int minute) {

            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
//            updateLabel();
        }
    }; }


