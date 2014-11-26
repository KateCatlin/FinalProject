package com.example.katecatlin.finalproject.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.adapters.ConcertListAdapter;
import com.example.katecatlin.finalproject.interfaces.JsonApiCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.requests.JSONRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 11/25/14.
 */
public class ConcertListFragment extends ListFragment implements JsonApiCallback {

    private ConcertListAdapter concertListAdapter;

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

    //onSuccess and onError may need to get refactored, depending on the passage of information from the
    //two sources of data!
    @Override
    public void onSuccess(JSONObject jsonObject) {
        List<ConcertModel> allConcerts = new ArrayList<ConcertModel>();

    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Error loading concerts, press back and try again!", Toast.LENGTH_LONG).show();

    }

    private void loadConcerts () {

        //this is gonna need to be refactored to include all the components...
        JSONRequest.getJsonRequest();

    }

}
