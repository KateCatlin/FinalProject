package com.example.katecatlin.finalproject.interfaces;

import com.example.katecatlin.finalproject.models.Concert;

import java.util.List;

/**
 * Created by katecatlin on 12/8/14.
 */
public interface MasterAPIRequestCallback {

    public void onSuccess(List<Concert> concertList);
    public void onError();
}
