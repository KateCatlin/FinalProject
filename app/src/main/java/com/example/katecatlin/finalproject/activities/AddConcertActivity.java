package com.example.katecatlin.finalproject.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWhen;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWho;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.models.ConcertModel;


public class AddConcertActivity extends Activity implements FragmentController {
    SubmitConcertWhen submitConcertWhen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        ConcertModel newConcert = new ConcertModel(null, "", "", "", "", "", "", "", "", "");

        SubmitConcertWho submitConcertWho = SubmitConcertWho.newInstance(newConcert);

        submitConcertWhen = new SubmitConcertWhen();

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
 }


