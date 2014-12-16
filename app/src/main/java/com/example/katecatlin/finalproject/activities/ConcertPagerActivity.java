package com.example.katecatlin.finalproject.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.ConcertDetailFragment;
import com.example.katecatlin.finalproject.models.Concert;

import java.util.ArrayList;

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

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        setActionBar();
        setViewPager();
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
            case R.id.action_refresh:
                Log.d("LOG_TAG", "button_home");
                intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.action_new_concert:
                Log.d("LOG_TAG", "new_concert");
                Intent addConcertIntent = new Intent (this, AddConcertActivity.class );
                startActivity(addConcertIntent);
                break;
        }
        return true;
    }

    public void setViewPager() {
        Bundle extras = getIntent().getExtras();
        int currentPosition = extras.getInt("position");
        concerts = extras.getParcelableArrayList("data");

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
