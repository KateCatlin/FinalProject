package com.example.katecatlin.finalproject.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.activities.AddConcertActivity;
import com.example.katecatlin.finalproject.activities.MainActivity;
import com.example.katecatlin.finalproject.models.ConcertModel;

/**
 * Created by katecatlin on 11/29/14.
 */


public class ConcertDetailFragment extends Fragment {

    private static final String ARG_CONCERT_ENTRY = "arg_concert_entry";
    private TextView detailArtistTextView, detailDateTextView, detailTimeTextView, detailVenueTextView,
            detailAddressTextView, detailCityTextView, detailZipCodeTextView, detailUrlTextView,
            featuringTextView, detailArtist2TextView, detailArtist3TextView;
    private Button ticketButton;

    public static ConcertDetailFragment newInstance(ConcertModel concertModel) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_CONCERT_ENTRY, concertModel);

        ConcertDetailFragment concertDetailFragment = new ConcertDetailFragment();
        concertDetailFragment.setArguments(args);

        return concertDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_view, container, false);

        detailArtistTextView = (TextView) view.findViewById(R.id.detailArtistTextView);
        detailDateTextView = (TextView) view.findViewById(R.id.detailDateTextView);
        detailTimeTextView = (TextView) view.findViewById(R.id.detailTimeTextView);
        detailVenueTextView = (TextView) view.findViewById(R.id.detailVenueTextView);
        detailAddressTextView = (TextView) view.findViewById(R.id.detailAddressTextView);
        detailCityTextView = (TextView) view.findViewById(R.id.detailCityTextView);
        detailZipCodeTextView = (TextView) view.findViewById(R.id.detailZipCodeTextView);
        detailUrlTextView = (TextView) view.findViewById(R.id.detailUrlTextView);
        featuringTextView = (TextView) view.findViewById(R.id.featuringTextView);
        detailArtist2TextView = (TextView) view.findViewById(R.id.detailArtist2TextView);
        detailArtist3TextView = (TextView) view.findViewById(R.id.detailArtist3TextView);
        ticketButton  = (Button) view.findViewById(R.id.Ticket_Button);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ConcertModel concertModel = getArguments().getParcelable(ARG_CONCERT_ENTRY);

        if (concertModel != null) {

            detailArtistTextView.setText(concertModel.getArtist1());
            detailDateTextView.setText(concertModel.convertDateTimetoDate(concertModel.getDateTime()));
            detailVenueTextView.setText(concertModel.getVenue());
            detailAddressTextView.setText(concertModel.getAddress());
            detailCityTextView.setText(concertModel.getCity());
            detailZipCodeTextView.setText(concertModel.getZipCode());
            detailUrlTextView.setText(concertModel.getTicketUrl());
            detailArtist2TextView.setText(concertModel.getArtist2());
            detailArtist3TextView.setText(concertModel.getArtist3());
            if (concertModel.getArtist2().equals("")) {
                featuringTextView.setText("");
            }
            if (concertModel.convertDateTimetoTime(concertModel.getDateTime()).toString().equals("0:00")) {
                detailTimeTextView.setVisibility(View.GONE);
            } else {
                detailTimeTextView.setText(concertModel.convertDateTimetoTime(concertModel.getDateTime()));
            }

            ticketButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (!concertModel.getTicketUrl().equals("")) {
                        openWebURL(concertModel.getTicketUrl());
                    }
                    else if (!concertModel.getVenueURL().equals("")) {
                        openWebURL(concertModel.getVenueURL());
                    } else {
                        Toast.makeText(getActivity(), "Sorry, URL not provided!", Toast.LENGTH_SHORT);
                    }
                }
            });
        } else {
            throw new IllegalStateException("Something went wrong passing concert model to detail fragment!");
        }
    }


    public void openWebURL(String URL ) {

        Intent internetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        internetIntent.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));
        internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(internetIntent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("LOG_TAG", "button_home from detail frag");
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
                break;
            case R.id.action_refresh:
                Log.d("LOG_TAG", "tried to refresh from detail fragment");
                break;
            case R.id.action_new_concert:
                Log.d("LOG_TAG", "new_concert");
                Intent addConcertIntent = new Intent (getActivity(), AddConcertActivity.class );
                startActivity(addConcertIntent);
                break;
            default:
                break;
        }
        return true;
    }

}
