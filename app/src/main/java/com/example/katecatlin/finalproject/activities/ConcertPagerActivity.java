package com.example.katecatlin.finalproject.activities;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.ConcertDetailFragment;
import com.example.katecatlin.finalproject.fragments.ConcertListFragment;
import com.example.katecatlin.finalproject.models.Concert;
import com.example.katecatlin.finalproject.parsers.SortConcertsByDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/15/14.
 */
public class ConcertPagerActivity extends FragmentActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ArrayList<Concert> concerts;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        Bundle extras = getIntent().getExtras();
        int currentPosition = extras.getInt("position");
        concerts = extras.getParcelableArrayList("data");
        Log.d("LOG_TAG", "current position is " + currentPosition);
        Log.d("LOG_TAG", "concerst include " + concerts.get(0).getArtist1());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        final ActionBar bar = getActionBar();
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public int getCount() {
                return concerts.size();
            }

            @Override
            public Fragment getItem(int position) {
                Concert concert = concerts.get(position);
                return ConcertDetailFragment.newInstance(concert);
            }
        });

        viewPager.setCurrentItem(currentPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged (int state) { }

            public void onPageScrolled (int pos, float posOffset, int posOffsetPixels) { }

            public void onPageSelected (int currentPosition) {
                Concert concert = concerts.get(currentPosition);
                if (concert.getArtist1() != null) {
                    setTitle(concert.getArtist1());
                }

            }
        });
    }




}
