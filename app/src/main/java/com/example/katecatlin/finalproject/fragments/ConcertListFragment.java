package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.activities.AddConcertActivity;
import com.example.katecatlin.finalproject.adapters.ConcertListAdapter;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.interfaces.MasterAPIRequestCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.parsers.SortConcertsByDate;
import com.example.katecatlin.finalproject.requests.MasterRequest;

import java.util.List;

/**
 * Created by katecatlin on 11/25/14.
 */
public class ConcertListFragment extends ListFragment implements MasterAPIRequestCallback {

    private ConcertListAdapter concertListAdapter;

    public ConcertListFragment () {
    }


    public static ConcertListFragment newInstance(){
        ConcertListFragment concertListFragment =  new ConcertListFragment();
        return concertListFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.concertListAdapter = new ConcertListAdapter(getActivity());
        setListAdapter(this.concertListAdapter);
        refreshConcerts();
    }

    public static final String FRAG_TAG = "ConcertListFragment";


    public void refreshConcerts () {
        MasterRequest masterRequest = MasterRequest.getMasterRequest(this);
        masterRequest.loadConcerts(getActivity());
    }


    @Override
    public void onListItemClick(ListView listView, View row, int position, long id) {

        if (getActivity() instanceof FragmentController) {

            ConcertModel concertModel = (ConcertModel) listView.getAdapter().getItem(position);
            ConcertDetailFragment concertDetailFragment = ConcertDetailFragment.newInstance(concertModel);

            FragmentController fragmentController = (FragmentController) getActivity();
            fragmentController.changeFragment(concertDetailFragment, true);

        } else {
            throw new IllegalArgumentException("Your activity must implement the FragmentController interface");
        }
    }


    @Override
    public void onSuccess(List<ConcertModel> returnedConcerts) {
        if (isAdded()) {

            SortConcertsByDate sortConcertsByDate = new SortConcertsByDate();
            final List<ConcertModel> sortedConcerts = sortConcertsByDate.sortConcerts(returnedConcerts);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((ConcertListAdapter)getListAdapter()).clear();
                    ((ConcertListAdapter)getListAdapter()).addAll(sortedConcerts);
                    ((ConcertListAdapter)getListAdapter()).notifyDataSetChanged();
                }
            });

        }
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Error loading concerts, press back and try again!", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("LOG_TAG", "button_home from listfrag");
                break;
            case R.id.action_refresh:
                Log.d("LOG_TAG", "action_refresh");
                Toast.makeText(getActivity(), "Refreshing your jams...", Toast.LENGTH_SHORT)
                        .show();
                refreshConcerts();
                break;
            case R.id.action_new_concert:
                Log.d("LOG_TAG", "new_concert");
                Intent addConcertIntent = new Intent (getActivity(), AddConcertActivity.class );
                startActivity(addConcertIntent);
                break;
            default:
                break;
        }
        return true;
    }
}
