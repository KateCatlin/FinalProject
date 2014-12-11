package com.example.katecatlin.finalproject.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.adapters.ConcertListAdapter;
import com.example.katecatlin.finalproject.fragments.ConcertListFragment;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.models.ConcertModel;


public class MainActivity extends Activity implements FragmentController {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        setActionBar();
        loadConcertListFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
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
        //*
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
        //*/
    }


    private void loadConcertListFragment () {

        //*
        Fragment clf = getFragmentManager().findFragmentByTag(ConcertListFragment.FRAG_TAG);
        if(clf != null) {
            Log.d("MainActivity", "found concert list fragment");
            getFragmentManager().beginTransaction().show(clf).commit();
            return;
        }

        ConcertListFragment concertListFragment = new ConcertListFragment();

        int result = getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, concertListFragment, ConcertListFragment.FRAG_TAG)
                .commit();


        Log.d("MainActivity", "load concert list fragment = " + result);
                //*/
    }

    public void setActionBar() {

        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.Heading);
        actionBar.show();
    }
}
