package com.example.katecatlin.finalproject.parsers;

import com.example.katecatlin.finalproject.models.ConcertModel;
import com.parse.ParseObject;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by katecatlin on 12/8/14.
 */
public class ParseObjectParser {

    public static List<ConcertModel> parseParseObject (ArrayList<ParseObject> parseObjects) {

        String venue, address, city, zipCode, venueURL, artist1, artist2, artist3, ticketUrl;
        DateTime dateTime;
        Date tempDate;
        ConcertModel concertModel;


        List <ConcertModel> concertsList = new ArrayList<ConcertModel>();

            for (ParseObject x: parseObjects) {

                tempDate = x.getDate("Date");
                dateTime = new DateTime(tempDate);
                venue = x.getString("Venue");
                address = x.getString("Address");
                city = x.getString("City");
                zipCode = x.getString("ZipCode");
                venueURL = x.getString("VenueURL");
                artist1 = x.getString("Artist1");
                artist2 = x.getString("Artist2");
                artist3 = x.getString("Artist3");
                ticketUrl = x.getString("TicketUrl");

                concertModel = new ConcertModel(dateTime, venue, address, city, zipCode, venueURL, artist1, artist2, artist3, ticketUrl);

                concertsList.add(concertModel);
            }

            return  concertsList;
    }
    }

