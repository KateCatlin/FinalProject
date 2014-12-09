package com.example.katecatlin.finalproject.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.example.katecatlin.finalproject.interfaces.GetChosenTimeInterface;

/**
 * Created by katecatlin on 12/4/14.
 */


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    GetChosenTimeInterface chosenTimeInterface;


    public TimePickerFragment (GetChosenTimeInterface getChosenTimeInterface) {
        chosenTimeInterface = getChosenTimeInterface;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int hour = 20;
        int minute = 00;

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    public void onTimeSet(TimePicker view, int hour, int minute) {
        chosenTimeInterface.getChosenTime(hour, minute);
    }
}