package com.example.katecatlin.finalproject.interfaces;

import com.example.katecatlin.finalproject.models.Concert;

import java.util.List;

/**
 * Created by katecatlin on 11/25/14.
 */
public interface IndividualApiRequestCallback {

    public void onSuccess(List<Concert> concertList);
    public void onError();

}
