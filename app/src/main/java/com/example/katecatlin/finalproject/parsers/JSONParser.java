package com.example.katecatlin.finalproject.parsers;

import android.util.Log;

import com.example.katecatlin.finalproject.models.ConcertModel;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 11/30/14.
 */
public class JSONParser {

    public static List <ConcertModel> parseJSONObject (JSONObject jsonObject) {

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("Events");

            List <ConcertModel> concertsList = new ArrayList<ConcertModel>();

            for (int index = 0; index < 10 && index < 25; index++) {

                JSONObject concertEntry = jsonArray.getJSONObject(index);

                ConcertModel newConcertModel = createConcertModelFromJsonInfo (concertEntry);

                concertsList.add(newConcertModel);

            }

            return  concertsList;

        } catch (JSONException e) {
           return new ArrayList<ConcertModel>();
        }

    }

    private static ConcertModel createConcertModelFromJsonInfo (JSONObject concertsJsonObject) throws JSONException {
        String venue, address, city, zipCode, venueURL, artist1, artist2, artist3, ticketUrl;
        DateTime dateTime;
        ConcertModel concertModel;
        JSONObject venueObject, artistObject;
        JSONArray artistArray;

        dateTime = new DateTime(concertsJsonObject.optString("Date"));

        venueObject = concertsJsonObject.getJSONObject("Venue");

        venue = venueObject.optString("Name", "Unknown Venue");
        address = venueObject.optString("Address", "Unknown Address");
        city = venueObject.optString("City", "Unknown City");
        zipCode = venueObject.optString("ZipCode", "Unknown ZIP code");
        venueURL = venueObject.optString("Url", "No URL");

        artistArray = concertsJsonObject.getJSONArray("Artists");

        //if artists don't exist, we want to display nothing!

        artistObject = artistArray.getJSONObject(0);
        artist1 = artistObject.optString("Name", "Unknown Artist");
        artist2 = "";
        artist3 = "";

        if (artistArray.length() > 1) {
            artistObject = artistArray.getJSONObject(1);
            artist2 = artistObject.optString("Name");
        }

        if (artistArray.length() > 2) {
            artistObject = artistArray.getJSONObject(2);
            artist3 = artistObject.optString("Name");
        }

        ticketUrl = concertsJsonObject.optString("TicketUrl", "Unknown URL");

        concertModel = new ConcertModel(dateTime,venue, address, city, zipCode,
                venueURL, artist1, artist2, artist3, ticketUrl);


        return concertModel;
    }
}
