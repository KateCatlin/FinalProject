package com.example.katecatlin.finalproject.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.example.katecatlin.finalproject.R;

import java.util.Calendar;

/**
 * Created by katecatlin on 12/4/14.
 */

public class DatePickerFragment extends DialogFragment
//        implements DatePickerDialog.OnDateSetListener
    {
    Button button_date;

//    public DatePickerFragment (Button button) {
//        button_date = button;
//    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the current date as the default date in the picker
//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//        // Create a new instance of DatePickerDialog and return it
//        return new DatePickerDialog(getActivity(), this, year, month, day);
//    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
    }
}