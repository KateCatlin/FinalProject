package com.example.katecatlin.finalproject.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.v4.app.FragmentActivity;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWhen;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWhere;
import com.example.katecatlin.finalproject.fragments.SubmitConcertWho;
import com.example.katecatlin.finalproject.models.ConcertModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/3/14.
 */
public class AddConcertActivity extends FragmentActivity {
    String [] tabTitles = new String [] {"WHO", "WHERE", "WHEN"};
    ActionBar actionBar;
    ViewPager viewPager;
    public List<android.support.v4.app.Fragment> fragmentList = new ArrayList<android.support.v4.app.Fragment>();
    ConcertModel submittedConcert;

    FragmentPageAdapter fragmentPageAdapter;
    android.support.v4.app.FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LOG_TAG", "Entered AddConcertActivity");
        submittedConcert = new ConcertModel()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        List<android.support.v4.app.Fragment> fragments = getFragments();

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), fragments);

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
                break;
            case 2:
                break;
            case 3:
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
