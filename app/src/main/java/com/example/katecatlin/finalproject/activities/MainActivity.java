package com.example.katecatlin.finalproject.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.ConcertListFragment;
import com.example.katecatlin.finalproject.interfaces.FragmentController;


public class MainActivity extends Activity implements FragmentController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                break;
            case R.id.action_new_concert:
                Intent addConcertIntent = new Intent (this, AddConcertActivity.class );
                startActivity(addConcertIntent);
                break;
            case R.id.action_settings:
                break;
            default:
                break;
        }
        return true;
    }
}
