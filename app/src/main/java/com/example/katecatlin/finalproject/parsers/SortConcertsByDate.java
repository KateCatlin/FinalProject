package com.example.katecatlin.finalproject.parsers;

import com.example.katecatlin.finalproject.models.ConcertModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by katecatlin on 12/8/14.
 */
public class SortConcertsByDate {

    public SortConcertsByDate () {
    }


    public List<ConcertModel> sortConcerts (List<ConcertModel> returnedConcerts) {

        Collections.sort(returnedConcerts, new Comparator<ConcertModel>() {
            public int compare(ConcertModel concertModel1, ConcertModel concertModel2) {
                if (concertModel1.getDateTime() == null || concertModel2.getDateTime() == null)
                    return 0;
                return concertModel1.getDateTime().compareTo(concertModel2.getDateTime());
            }
        });
        return returnedConcerts;
    }
}
