package com.example.katecatlin.finalproject.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.models.ConcertModel;

/**
 * Created by katecatlin on 11/26/14.
 */
public class ConcertListAdapter extends ArrayAdapter<ConcertModel> {

    public ConcertListAdapter (Context context) {
        super(context, R.layout.rows_concert_listings);
    }

}
