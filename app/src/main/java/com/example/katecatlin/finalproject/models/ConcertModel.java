package com.example.katecatlin.finalproject.models;

import org.joda.time.DateTime;

/**
 * Created by katecatlin on 11/25/14.
 */
public class ConcertModel {
    DateTime DateTime;
    String Venue;
    String Address;
    String City;
    String ZipCode;
    String VenueURL;
    String Artist1;
    String Artist2;
    String Artist3;
    String TicketUrl;

    public ConcertModel(DateTime dateTime, String venue, String address, String city, String zipCode, String venueURL, String artist1, String artist2, String artist3, String ticketUrl) {
        DateTime = dateTime;
        Venue = venue;
        Address = address;
        City = city;
        ZipCode = zipCode;
        VenueURL = venueURL;
        Artist1 = artist1;
        Artist2 = artist2;
        Artist3 = artist3;
        TicketUrl = ticketUrl;
    }



    public String getVenue() {
        return Venue;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public String getVenueURL() {
        return VenueURL;
    }

    public String getArtist1() {
        return Artist1;
    }

    public String getArtist2() {
        return Artist2;
    }

    public String getArtist3() {
        return Artist3;
    }

    public String getTicketUrl() {
        return TicketUrl;
    }
}
