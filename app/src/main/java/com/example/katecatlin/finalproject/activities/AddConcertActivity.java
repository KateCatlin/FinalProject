package com.example.katecatlin.finalproject.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWhen;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWhere;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWho;
import com.example.katecatlin.finalproject.models.ConcertModel;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by katecatlin on 12/3/14.
 */
public class AddConcertActivity extends FragmentActivity {
    String [] tabTitles = new String [] {"WHO", "WHERE", "WHEN"};
    ActionBar actionBar;
    ViewPager viewPager;
    public List<android.support.v4.app.Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>();
    public ConcertModel submittedConcert;

    FragmentPageAdapter fragmentPageAdapter;
    android.support.v4.app.FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);
        List<android.support.v4.app.Fragment> fragments = getFragments();
        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), fragments);

        Date currentDate = new Date(System.currentTimeMillis());

        DateTime dateTime = new DateTime(currentDate);
        submittedConcert = new ConcertModel(dateTime, "", "", "", "", "", "", "", "", "");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(fragmentPageAdapter);

        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener listener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//                if(mFragment != null)
//                {
                    saveData(fragmentList.get(tab.getPosition()), tab.getPosition());
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };

        for (String tab_name : tabTitles) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(listener));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d("LOG_TAG", "onPageSelected position is " + position);
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        Button submitButton = (Button) findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
        Log.d("LOG_TAG", "Info is " + submittedConcert.getArtist1() + " " + submittedConcert.getVenue() + " " + submittedConcert.getAddress());
                                            }
                                        }



        );
    }

    private class FragmentPageAdapter extends FragmentPagerAdapter {
        private List<android.support.v4.app.Fragment> fragments;

        public FragmentPageAdapter(FragmentManager fm, List<android.support.v4.app.Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            Log.d("LOG_TAG", "getItem position is " + position);
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("LOG_TAG", "getPageTitle position is " + position);
            return tabTitles[position];
        }
    }


    private List<android.support.v4.app.Fragment> getFragments(){

        fragmentList.add(SubmitConcertWho.newInstance("Fragment 1"));
        fragmentList.add(SubmitConcertWhere.newInstance("Fragment 2"));
        fragmentList.add(SubmitConcertWhen.newInstance("Fragment 3"));

        return fragmentList;
    }


    private void saveData(android.support.v4.app.Fragment fragment, int position){

        switch ( position ) {
            case 1:

                EditText edit_artist_1 = (EditText) findViewById(R.id.edit_artist_1);
                String artist1 = edit_artist_1.getText().toString();
                submittedConcert.setArtist1(artist1);

                EditText edit_artist_2 = (EditText) findViewById(R.id.edit_artist_2);
                String artist2 = edit_artist_2.getText().toString();
                submittedConcert.setArtist2(artist2);

                EditText edit_artist_3 = (EditText) findViewById(R.id.edit_artist_3);
                String artist3 = edit_artist_3.getText().toString();
                submittedConcert.setArtist3(artist3);

                break;

            case 2:

                EditText edit_venue = (EditText) findViewById(R.id.edit_venue);
                String venue = edit_venue.getText().toString();
                submittedConcert.setVenue(venue);

                EditText edit_address = (EditText) findViewById(R.id.edit_address);
                String address = edit_address.getText().toString();
                submittedConcert.setAddress(address);

                EditText edit_city = (EditText) findViewById(R.id.edit_city);
                String city = edit_city.getText().toString();
                submittedConcert.setCity(city);

                EditText edit_zipcode = (EditText) findViewById(R.id.edit_zipcode);
                String zipCode = edit_zipcode.getText().toString();
                submittedConcert.setZipCode(zipCode);

                EditText edit_venue_url = (EditText) findViewById(R.id.edit_venue_url);
                String venueURL = edit_venue_url.getText().toString();
                submittedConcert.setVenueURL(venueURL);

                break;


            case 3:

                EditText edit_ticket_url = (EditText) findViewById(R.id.edit_ticket_url);
                String url = edit_ticket_url.getText().toString();
                submittedConcert.setTicketUrl(url);

                break;

            default:
                break;

        }
    }

//    @Override
//    public void changeFragment(Fragment fragment, boolean addToBackstack) {
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//
//        if (addToBackstack) {
//            fragmentTransaction.addToBackStack(null);
//        }
//
//        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
//        fragmentTransaction.commit();
//    }

}
