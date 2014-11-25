package com.example.katecatlin.finalproject.fragments;

import android.app.ListFragment;

import com.example.katecatlin.finalproject.interfaces.JsonApiCallback;

import org.json.JSONObject;

/**
 * Created by katecatlin on 11/25/14.
 */
public class ConcertListFragment extends ListFragment implements JsonApiCallback {

    @Override
    public void onSuccess(JSONObject jsonObject) {

    }

    @Override
    public void onError() {

    }
}
