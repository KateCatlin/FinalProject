package com.example.katecatlin.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.models.ConcertModel;

import org.joda.time.DateTime;

/**
 * Created by katecatlin on 11/26/14.
 */
public class ConcertListAdapter extends ArrayAdapter<ConcertModel> {
    Context context;

    public ConcertListAdapter (Context context) {
        super(context, R.layout.rows_concert_listings);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType (int position) {
        return (position == 0) ? 0 : 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        int type = getItemViewType(position);
        final int FIRST_CONCERT_VIEW = 0;
        final int OTHER_CONCERT_VIEW = 1;

        if (convertView == null) {
            viewHolder = new ViewHolder(convertView);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (type) {
                case FIRST_CONCERT_VIEW:
                    convertView = inflater.inflate(R.layout.first_row_concert_listing, parent, false);
                    break;
                case OTHER_CONCERT_VIEW:
                    convertView = inflater.inflate(R.layout.rows_concert_listings, parent, false);
                    break;
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ConcertModel concertModel = getItem(position);

        viewHolder.textView_artist1.setText(concertModel.getArtist1());
        viewHolder.textView_city.setText(concertModel.getCity());
        viewHolder.textView_venue.setText(concertModel.getVenue());
        DateTime dateTime = concertModel.getDateTime();
        viewHolder.textView_date.setText(concertModel.convertDateTimetoDate(dateTime));
        viewHolder.textView_time.setText(concertModel.convertDateTimetoTime(dateTime));

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
