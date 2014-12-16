package com.example.katecatlin.finalproject.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWho;
import com.example.katecatlin.finalproject.interfaces.FragmentControllerNewConcert;
import com.example.katecatlin.finalproject.models.Concert;


public class AddConcertActivity extends Activity implements FragmentControllerNewConcert {
    public static Concert newConcert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        setActionBar();

        newConcert = new Concert();

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
    public void changeFragment(Fragment fragment, boolean addToBackstack, Concert newConcert) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        this.newConcert = newConcert;
        Log.d("LOG_TAG", this.newConcert.getArtist1() + this.newConcert.getCity() + this.newConcert.getAddress() + this.newConcert.getDateTime().toString());


        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void setActionBar() {

        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.Heading);
        actionBar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("LOG_TAG", "button_home");
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
        }
        return true;
    }

 }


