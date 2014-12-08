package com.example.katecatlin.finalproject.requests;

import android.app.Activity;

import com.example.katecatlin.finalproject.fragments.ConcertListFragment;
import com.example.katecatlin.finalproject.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.finalproject.interfaces.MasterAPIRequestCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.parsers.ParseObjectParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/8/14.
 */
public class MasterRequest implements IndividualApiRequestCallback {
    public static Integer apisReturned = 0;
    List<ConcertModel> upcomingConcerts = new ArrayList<ConcertModel>();
    private MasterAPIRequestCallback masterAPIRequestCallback;

    private static MasterRequest masterRequest;
    Activity activity;

    public static MasterRequest getMasterRequest() {
        if (masterRequest == null) {
            masterRequest = new MasterRequest();
        }
        return masterRequest;
    }

    private MasterRequest() {
    }

    public void loadConcerts(Activity activity, MasterAPIRequestCallback masterAPIRequestCallback) {
        this.masterAPIRequestCallback = masterAPIRequestCallback;


        JSONRequest jsonRequest = JSONRequest.getJsonRequest();
        jsonRequest.getConcerts(this);

        ParseRequest parseRequest = ParseRequest.getParseRequest(activity, this);
        parseRequest.getConcertsFromParse(this);
    }

    public void refreshConcerts (List<ConcertModel> returnedConcerts) {

        for (int i=0; i < 10; i++) {
            upcomingConcerts.add(returnedConcerts.get(i));
        }

        if (apisReturned == 1) {
            if (upcomingConcerts.get(0) != null) {
                apisReturned = 0;
                masterAPIRequestCallback.onSuccess(upcomingConcerts);
            } else {
                masterAPIRequestCallback.onError();
            }
        }
    }

    @Override
    public void onSuccess(List<ConcertModel> concertModelList) {
        apisReturned++;
        refreshConcerts(concertModelList);
    }

    @Override
    public void onError() {
        masterAPIRequestCallback.onError();
    }
}
