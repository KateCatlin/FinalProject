package com.example.katecatlin.finalproject.interfaces;

import android.app.Fragment;

import com.example.katecatlin.finalproject.models.Concert;

/**
 * Created by katecatlin on 12/10/14.
 */
public interface FragmentControllerNewConcert {

    public void changeFragment(Fragment fragment, boolean addToBackstack, Concert concert);

}
