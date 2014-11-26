package com.example.katecatlin.finalproject.fragments;

import android.app.ListFragment;
import android.os.Bundle;

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

    public ConcertListFragment () {
    }


    public static ConcertListFragment newInstance(){
        ConcertListFragment concertListFragment =  new ConcertListFragment();
        return concertListFragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //Go back and refactor this!
        JSONRequest.getJsonRequest();

    }

    @Override
    public void onSuccess(JSONObject jsonObject) {
        List<ConcertModel> allConcerts = new ArrayList<ConcertModel>();

    }

    @Override
    public void onError() {

    }
}
