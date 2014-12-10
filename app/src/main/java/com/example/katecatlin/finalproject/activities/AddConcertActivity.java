package com.example.katecatlin.finalproject.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWhen;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWho;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.interfaces.FragmentControllerNewConcert;
import com.example.katecatlin.finalproject.models.ConcertModel;


public class AddConcertActivity extends Activity implements FragmentControllerNewConcert {
    public static ConcertModel newConcert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        newConcert = new ConcertModel();

        SubmitConcertWho submitConcertWho = SubmitConcertWho.newInstance();

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
    public void changeFragment(Fragment fragment, boolean addToBackstack, ConcertModel newConcert) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        this.newConcert = newConcert;
        Log.d("LOG_TAG", this.newConcert.getArtist1() + this.newConcert.getCity() + this.newConcert.getAddress() + this.newConcert.getDateTime().toString());


        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
 }


