package com.example.katecatlin.finalproject.requests;

import android.app.Activity;
import android.util.Log;

import com.example.katecatlin.finalproject.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.parsers.ParseObjectParser;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by katecatlin on 12/5/14.
 */
public class ParseRequest {
    private static String APPLICATION_ID = "Farfo7O7tFBYG7ZqhhUr3Qxp0rzUGDbqiYNJaWwX";
    private static String CLIENT_KEY = "3AN5tiVBtJ24e5J0RUx1DNf1cpeXBJiQqrdigEif";

    private static ParseRequest parseRequest;
    private IndividualApiRequestCallback individualApiRequestCallback;



    public ParseRequest(Activity currentActivity) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
    }

    public static ParseRequest getParseRequest (Activity activity) {
        if (parseRequest == null) {
            parseRequest = new ParseRequest(activity);
        }
        return parseRequest;
    }

    public void postConcertToParse(ConcertModel newConcert) {

        Log.d("LOG_TAG", "newConcertObject is " + newConcert.getArtist1() + " " + newConcert.getAddress());
        ParseObject newConcertObject = new ParseObject("Concert");
//            newConcertObject.put("Date", newConcert.getDateTime().toDate());
        newConcertObject.put("Venue", newConcert.getVenue());
        newConcertObject.put("Address", newConcert.getAddress());
        newConcertObject.put("City", newConcert.getCity());
        newConcertObject.put("ZipCode", newConcert.getZipCode());
        newConcertObject.put("VenueURL", newConcert.getVenueURL());
        newConcertObject.put("Artist1", newConcert.getArtist1());
        newConcertObject.put("Artist2", newConcert.getArtist2());
        newConcertObject.put("Artist3", newConcert.getArtist3());
        newConcertObject.put("TicketUrl", newConcert.getTicketUrl());
        newConcertObject.saveInBackground();
    }

    public void getConcertsFromParse(IndividualApiRequestCallback individualApiRequestCallback) {
        this.individualApiRequestCallback = individualApiRequestCallback;


        List<ParseObject> parseObjectList = null;
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Concert");
        ArrayList<ParseObject> parseObjects = new ArrayList<ParseObject>();
        List<ConcertModel> concertModels = null;

        try {
            parseObjectList = parseQuery.find();

            for (ParseObject x: parseObjectList) {
                parseObjects.add(x);
            }

            ParseObjectParser parseObjectParser = new ParseObjectParser();
            concertModels = parseObjectParser.parseParseObject(parseObjects);

        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        if (concertModels != null) {
            this.individualApiRequestCallback.onSuccess(concertModels);
        } else {
            this.individualApiRequestCallback.onError();
        }
    }

    //DO SOMETHING WITH PARSING

}
