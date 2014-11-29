package com.example.katecatlin.finalproject.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.ConcertListFragment;
import com.example.katecatlin.finalproject.interfaces.FragmentController;


public class MainActivity extends Activity implements FragmentController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadConcertListFragment();
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

    private void loadConcertListFragment () {

        ConcertListFragment concertListFragment = ConcertListFragment.newInstance();

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, concertListFragment)

                .commit();

    }
}
