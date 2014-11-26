package com.example.katecatlin.finalproject.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.ConcertListFragment;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment concertListFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);

        if (concertListFragment == null) {
            concertListFragment = ConcertListFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, concertListFragment )
                    .commit();
        }
    }

}
