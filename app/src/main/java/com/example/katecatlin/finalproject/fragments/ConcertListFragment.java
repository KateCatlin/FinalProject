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
    public void onSuccess() {
        List<ConcertModel> allConcerts = new ArrayList<ConcertModel>();

        //Making a fake array just to test array adapter:
        ConcertModel concertModel1 = new ConcertModel("12/25", "9:32PM", "The Venue", "1520 Woodward Ave", "Detroit", "48226", "DetroitLabs.com", "The Rocking Rok", "Two-Tones Tyndall", "Stu Mcgoo", "www.RockinLabs.com");
        ConcertModel concertModel2 = new ConcertModel("12/25", "9:32PM", "La Venue-a", "1521 Woodward Ave", "Detroit", "48226", "DetroitLabers.com", "Bay-Bay Bry Bry", "Chowdown Chowning", "The Clawed", "www.Labs.com");
        ConcertModel concertModel3 = new ConcertModel("12/25", "9:32PM", "El Venue-y", "1522 Woodward Ave", "Detroit", "48226", "DetroitLabays.com", "Sub-Bass Sibs", "No Dotz", "Terry-Your-Heart-Out", "www.RockinLabs.com");

        allConcerts.add(concertModel1);
        allConcerts.add(concertModel2);
        allConcerts.add(concertModel3);

        concertListAdapter.clear();
        concertListAdapter.addAll(allConcerts);

    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Error loading concerts, press back and try again!", Toast.LENGTH_LONG).show();

    }

    private void loadConcerts () {

        onSuccess();

        //this is gonna need to be refactored to include all the components...
//        JSONRequest.getJsonRequest();

    }

}
