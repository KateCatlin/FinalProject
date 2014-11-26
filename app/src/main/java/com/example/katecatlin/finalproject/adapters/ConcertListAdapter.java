package com.example.katecatlin.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rows_concert_listings, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ConcertModel concertModel = getItem(position);

        viewHolder.


        return convertView;
    }

    private static class ViewHolder {

        public ViewHolder(View rootView) {

        }
    }

}
