package com.example.katecatlin.finalproject.requests;

import android.app.Activity;

import com.example.katecatlin.finalproject.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.finalproject.interfaces.MasterAPIRequestCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/8/14.
 */
public class MasterRequest implements IndividualApiRequestCallback {

    public static Integer apisReturned = 0;
    List<ConcertModel> upcomingConcerts = new ArrayList<ConcertModel>();
    private MasterAPIRequestCallback thisMasterAPIRequestCallback;
    private static MasterRequest masterRequest;


    public static MasterRequest getMasterRequest(MasterAPIRequestCallback masterAPIRequestCallback) {
        if (masterRequest == null) {
            masterRequest = new MasterRequest(masterAPIRequestCallback);
        }
        return masterRequest;
    }


    private MasterRequest(MasterAPIRequestCallback masterAPIRequestCallback) {
        thisMasterAPIRequestCallback = masterAPIRequestCallback;
    }


    public void loadConcerts(Activity activity, MasterAPIRequestCallback masterAPIRequestCallback) {
        thisMasterAPIRequestCallback = masterAPIRequestCallback;

        JSONRequest jsonRequest = JSONRequest.getJsonRequest(this);
        jsonRequest.getConcerts();

        ParseRequest parseRequest = ParseRequest.getParseRequest(activity, this);
        parseRequest.getConcertsFromParse();
    }


    public void refreshConcerts (List<ConcertModel> returnedConcerts) {

        for (int i=0; i < returnedConcerts.size(); i++) {
            upcomingConcerts.add(returnedConcerts.get(i));
        }

        if (apisReturned == 1) {
            if (upcomingConcerts.get(0) != null) {
                apisReturned = 0;
                thisMasterAPIRequestCallback.onSuccess(upcomingConcerts);
            } else {
                thisMasterAPIRequestCallback.onError();
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
        thisMasterAPIRequestCallback.onError();
    }
}
