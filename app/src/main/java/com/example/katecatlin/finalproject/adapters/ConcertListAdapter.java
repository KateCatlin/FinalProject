package com.example.katecatlin.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        viewHolder.textView_artist1.setText(concertModel.getArtist1());
//        viewHolder.textView_date.setText(concertModel.);


        return convertView;
    }

    private static class ViewHolder {

        private TextView textView_artist1;
        private TextView textView_date;
        private TextView textView_time;
        private TextView textView_venue;
        private TextView textView_city;

        public ViewHolder(View rootView) {
            this.textView_artist1 = (TextView) rootView.findViewById(R.id.TextView_Artist1);
            this.textView_date = (TextView) rootView.findViewById(R.id.TextView_Date);
            this.textView_time = (TextView) rootView.findViewById(R.id.TextView_Time);
            this.textView_venue = (TextView) rootView.findViewById(R.id.TextView_Venue);
            this.textView_city = (TextView) rootView.findViewById(R.id.TextView_City);
        }
    }

}
