package com.example.katecatlin.finalproject.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.activities.AddConcertActivity;
import com.example.katecatlin.finalproject.adapters.ConcertListAdapter;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.finalproject.interfaces.MasterAPIRequestCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.parsers.ParseObjectParser;
import com.example.katecatlin.finalproject.requests.JSONRequest;
import com.example.katecatlin.finalproject.requests.MasterRequest;
import com.example.katecatlin.finalproject.requests.ParseRequest;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 11/25/14.
 */
public class ConcertListFragment extends ListFragment implements MasterAPIRequestCallback {

    private ConcertListAdapter concertListAdapter;
    MasterRequest masterRequest;
    MasterAPIRequestCallback masterAPIRequestCallback;

    public ConcertListFragment () {
    }


    public static ConcertListFragment newInstance(){
        ConcertListFragment concertListFragment =  new ConcertListFragment();
        return concertListFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        concertListAdapter = new ConcertListAdapter(getActivity());
        setListAdapter(concertListAdapter);
        refreshConcerts();
    }

    public void refreshConcerts () {
        masterRequest = MasterRequest.getMasterRequest(this);
        masterRequest.loadConcerts(getActivity(), this);
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
            concertListAdapter.clear();
            concertListAdapter.addAll(returnedConcerts);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Error loading concerts, press back and try again!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(getActivity(), "Refreshing your jams...", Toast.LENGTH_SHORT)
                        .show();
                refreshConcerts();
                break;
            case R.id.action_new_concert:
                Intent addConcertIntent = new Intent (getActivity(), AddConcertActivity.class );
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
