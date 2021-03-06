package com.example.katecatlin.finalproject.requests;

import android.app.Activity;

import com.example.katecatlin.finalproject.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.finalproject.interfaces.MasterAPIRequestCallback;
import com.example.katecatlin.finalproject.models.Concert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/8/14.
 */
public class MasterRequest implements IndividualApiRequestCallback {

    public static Integer apisReturned = 0;
    List<Concert> upcomingConcerts = new ArrayList<Concert>();
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


    public void loadConcerts(Activity activity, String zipcode, String radius) {

        upcomingConcerts.clear();

        JSONRequest jsonRequest = JSONRequest.getJsonRequest(this);
        jsonRequest.getConcerts(zipcode, radius);

        ParseRequest parseRequest = ParseRequest.getParseRequest(activity, this);
        parseRequest.getConcertsFromParse();
    }


    public void refreshConcerts (List<Concert> returnedConcerts) {

        if (returnedConcerts != null) {
            for (int i = 0; i < returnedConcerts.size(); i++) {
                upcomingConcerts.add(returnedConcerts.get(i));
            }
        }

        if (apisReturned == 2) {
            try {
                if (upcomingConcerts.get(0) != null) {
                    apisReturned = 0;
                    thisMasterAPIRequestCallback.onSuccess(upcomingConcerts);
                } else {
                    thisMasterAPIRequestCallback.onError();
                }
            } catch (IndexOutOfBoundsException e) {
                thisMasterAPIRequestCallback.onError();
            }


        }
    }

    @Override
    public void onSuccess(List<Concert> concertList) {
        apisReturned++;
        refreshConcerts(concertList);
    }


    @Override
    public void onError() {
        apisReturned++;
        refreshConcerts(null);
    }
}
