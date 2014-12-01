package com.example.katecatlin.finalproject.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.adapters.ConcertListAdapter;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.interfaces.JsonApiCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.requests.JSONRequest;

import org.joda.time.DateTime;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 11/25/14.
 */
public class ConcertListFragment extends ListFragment implements JsonApiCallback {

    private ConcertListAdapter concertListAdapter;
    private JSONRequest jsonRequest = JSONRequest.getJsonRequest();

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
        loadConcerts();
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

    //onSuccess and onError may need to get refactored, depending on the passage of information from the
    //two sources of data!
    @Override
    public void onSuccess(List<ConcertModel> upcomingConcerts) {


        /// Ask Matt why "isAdded" is used here!
        if (isAdded()) {
            concertListAdapter.clear();
            concertListAdapter.addAll(upcomingConcerts);
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Error loading concerts, press back and try again!", Toast.LENGTH_LONG).show();

    }

    private void loadConcerts () {

        //this will be changed later to ALSO call entries from the Parse.com API, which will be added later.
        jsonRequest.getConcerts(this);


    }

}
