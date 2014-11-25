package com.example.katecatlin.finalproject.models;

/**
 * Created by katecatlin on 11/25/14.
 */
public class ConcertModel {
    String Venue;
    String Address;
    String City;
    String ZipCode;
    String VenueURL;
    String Artist1;
    String Artist2;
    String Artist3;
    String TicketUrl;

    public ConcertModel(String venue, String address, String city, String zipCode, String venueURL, String artist1, String artist2, String artist3, String ticketUrl) {
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
}
