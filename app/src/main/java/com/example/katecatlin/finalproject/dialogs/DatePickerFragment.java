package com.example.katecatlin.finalproject.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.interfaces.GetChosenDateInterface;

import java.util.Calendar;

/**
 * Created by katecatlin on 12/4/14.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    GetChosenDateInterface chosenDateInterface;
    String EXTRA_DATE = "EXTRA_DATE";


    public DatePickerFragment (GetChosenDateInterface getChosenDateInterface) {
        chosenDateInterface = getChosenDateInterface;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    public void onDateSet(DatePicker view, int year, int month, int day) {
        chosenDateInterface.getChosenDate(year, month, day);
    }
}